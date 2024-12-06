package com.bootcamp.services;

import com.bootcamp.dto.UFDTO;
import com.bootcamp.entities.UF;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.repositories.UFRepository;
import com.bootcamp.mapper.UFMapper;
import com.bootcamp.util.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UFService {

    @Autowired
    private UFRepository ufRepository;
    @Autowired
    private UFMapper ufMapper;

    public List<UF> findAll() {
        return ufRepository.findAll();
    }



    public Object findOne(Long codigoUF, String Sigla, String nome, Integer status) {
        //Cria um objeto UF com os parâmetros informados
        UF ufModel = new UF(codigoUF, Sigla, nome, status);

        //Busca a UF no banco de dados com base no objeto criado
        var search = ufRepository.findOne(
                Example.of(ufModel));
        //Se encontrar a UF, retorna o objeto
        if (search.isPresent()) {
            return search.get();
        }
        //Se não encontrar a UF, retorna uma lista vazia
        return new ArrayList<>();
    }
    public List<UF> findAll(Integer status) {
  //Cria um objeto UF com o status informado
        UF uf = new UF();
        //Define o status do objeto
        uf.setStatus(status);
        //Retorna a lista de UFs com base no objeto criado
        return ufRepository.findAll(Example.of(uf));
    }



//    public Object getFindById(Long codigoUF) {
//        UF uf = ufRepository.findById(codigoUF).orElse(null);
//        //Se não encontrar a UF, retorna uma lista vazia
//        if (uf == null) {
//            return new ArrayList<>(); //retorna uma lista vazia
//        }
//        //Se encontrar a UF, retorna o objeto
//        return ufMapper.toDTO(uf);
//    }
//
//    public Object getFindBySigla(String sigla) {
//        //Converte a sigla para maiúsculo
//        String siglaUpper = sigla.toUpperCase();
//        UF uf = ufRepository.findBySigla(siglaUpper).orElse(null);
//        if (uf == null) {
//            return new ArrayList<>(); //retorna uma lista vazia
//        }
//        return ufMapper.toDTO(uf);
//    }
//
//    public Object getFindByNome(String nome) {
//        //Converte o nome para maiúsculo
//        String nomeUpper = nome.toUpperCase();
//        UF uf = ufRepository.findByNome(nomeUpper).orElse(null);
//        if (uf == null) {
//            return new ArrayList<>(); //retorna um objeto vazio
//        }
//        return ufMapper.toDTO(uf);
//    }
public List<UFDTO> getAll(Long codigoUF, String nome, String sigla, Integer status) {
    List<UF> ufs = ufRepository
            .findAll()
            .stream()
            .filter(a -> (codigoUF == null || a.getCodigoUF().equals(codigoUF) )
                    && (nome == null || a.getNome().equalsIgnoreCase(nome))
                    && (sigla == null || a.getSigla().equalsIgnoreCase(sigla))
                    && (status == null || a.getStatus() == status))
            .collect(Collectors.toList());


    return ufs.stream()
            .map(ufMapper::toDTO)
            .collect(Collectors
                    .toList());
}

    @Transactional
    public List<UFDTO> create(UFDTO ufDTO){
        //Validações
        ValidateUtils.validateStatus(ufDTO.getStatus());
        ValidateUtils.validateNome(ufDTO.getNome());
        ValidateUtils.validateSigla(ufDTO.getSigla());

        ufDTO.setNome(ufDTO.getNome().trim());// Remove espaços do nome


        //Valida se já existe um registro com o mesmo nome ou sigla

        if (ufRepository.existsByNome(ufDTO.getNome())) {
            throw new DesafioException("Não foi possível incluir UF no banco de dados. Motivo: já existe um(a) registro com o nome "+ufDTO.getNome()+ " no banco de dados.");
        }
        if (ufRepository.existsBySigla(ufDTO.getSigla())) {
            throw new DesafioException("Não foi possível incluir UF no banco de dados. Motivo: já existe um(a) registro com a sigla "+ufDTO.getSigla()+ " no banco de dados.");
        }


        UF uf = ufMapper.toEntity(ufDTO);
        ufRepository.save(uf);
        List<UFDTO> list = getAll(null, null, null, null);//retorna a lista atualizada
        return list;
    }

    @Transactional
    public List<UFDTO> update(UFDTO ufDTO){
        //Validações

        ValidateUtils.validateSigla(ufDTO.getSigla());
        ValidateUtils.validateNome(ufDTO.getNome());
        ValidateUtils.validateStatus(ufDTO.getStatus());
        ValidateUtils.validatecodigoUF(ufDTO.getCodigoUF());

        ufDTO.setNome(ufDTO.getNome().trim());// Remove espaços do nome
        ufDTO.setSigla(ufDTO.getSigla().trim());// Remove espaços da sigla



        if (!ufRepository.existsByCodigoUF(ufDTO.getCodigoUF())) {//verifica se o código da UF existe
            throw new DesafioException("Não foi possível atualizar UF. Motivo: código da UF " + ufDTO.getCodigoUF() + " não foi encontrado no banco.");
        }


        // Verifica se o nome já existe e se o código da UF é diferente
        UF existingUFByName = ufRepository.findByNome(ufDTO.getNome()).orElse(null);
        //Se já existir um registro com o mesmo nome e o código da UF for diferente, retorna uma exceção
        if (existingUFByName != null && !existingUFByName.getCodigoUF().equals(ufDTO.getCodigoUF())) {
            throw new DesafioException("Não foi possível atualizar UF. Motivo: já existe um(a) registro com o nome " + ufDTO.getNome() + " no banco de dados.");
        }

        // Verifica se a sigla já existe e se o código da UF é diferente
        UF existingUFBySigla = ufRepository.findBySigla(ufDTO.getSigla()).orElse(null);
        //Se já existir um registro com a mesma sigla e o código da UF for diferente, retorna uma exceção
        if (existingUFBySigla != null && !existingUFBySigla.getCodigoUF().equals(ufDTO.getCodigoUF())) {
            throw new DesafioException("Não foi possível atualizar UF. Motivo: já existe um(a) registro com a sigla " + ufDTO.getSigla() + " no banco de dados.");
        }




        UF uf = ufMapper.toEntity(ufDTO);
        ufRepository.save(uf);
        List<UFDTO> list = getAll(null, null, null, null);//retorna a lista atualizada
        return list;
    }

}
