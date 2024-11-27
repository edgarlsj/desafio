package com.bootcamp.services;

import com.bootcamp.dto.UFDTO;
import com.bootcamp.entities.UF;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.repositories.UFRepository;
import com.bootcamp.mapper.UFMapper;
import com.bootcamp.util.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class UFService {

    @Autowired
    private UFRepository ufRepository;
    @Autowired
    private UFMapper ufMapper;


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


    public Object getFindById(Long codigoUF) {
        UF uf = ufRepository.findById(codigoUF).orElse(null);
        if (uf == null) {
            return new ArrayList<>(); //retorna uma lista vazia
        }
        return ufMapper.toDTO(uf);
    }

    public Object getFindBySigla(String sigla) {
        //Converte a sigla para maiúsculo
        String siglaUpper = sigla.toUpperCase();
        UF uf = ufRepository.findBySigla(siglaUpper).orElse(null);
        if (uf == null) {
            return new ArrayList<>(); //retorna uma lista vazia
        }
        return ufMapper.toDTO(uf);
    }

    public Object getFindByNome(String nome) {
        //Converte o nome para maiúsculo
        String nomeUpper = nome.toUpperCase();
        UF uf = ufRepository.findByNome(nomeUpper).orElse(null);
        if (uf == null) {
            return new ArrayList<>(); //retorna um objeto vazio
        }
        return ufMapper.toDTO(uf);
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



        if (!ufRepository.existsByCodigoUF(ufDTO.getCodigoUF())) {//verifica se o código da UF existe
            throw new DesafioException("Não foi possível atualizar UF. Motivo: código da UF " + ufDTO.getCodigoUF() + " não foi encontrado no banco.");
        }


        UF uf = ufMapper.toEntity(ufDTO);
        ufRepository.save(uf);
        List<UFDTO> list = getAll(null, null, null, null);//retorna a lista atualizada
        return list;
    }

}
