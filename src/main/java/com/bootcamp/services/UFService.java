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


    public UFDTO getFindById (Long codigoUF){
        UF uf = ufRepository.findById(codigoUF).orElseThrow(() -> new DesafioException("Não foi possível consultar UF no banco de dados."));
        return ufMapper.toDTO(uf);
    }

    @Transactional
    public List<UFDTO> create(UFDTO ufDTO){
        //Validações
        ValidateUtils.validateStatus(ufDTO.getStatus());
        ValidateUtils.validateNome(ufDTO.getNome());
        ValidateUtils.validateSigla(ufDTO.getSigla());


        //Valida se já existe um registro com o mesmo nome ou sigla

        if (ufRepository.existsByNome(ufDTO.getNome())) {
            throw new DesafioException("Não foi possível incluir UF no banco de dados. Motivo: já existe um(a) registro com o nome no banco de dados.");
        }
        if (ufRepository.existsBySigla(ufDTO.getSigla())) {
            throw new DesafioException("Não foi possível incluir UF no banco de dados. Motivo: já existe um(a) registro com a sigla no banco de dados.");
        }


        UF uf = ufMapper.toEntity(ufDTO);
        ufRepository.save(uf);
        List<UFDTO> list = getAll(null, null, null, null);//retorna a lista atualizada
        return list;
    }

    @Transactional
    public List<UFDTO> update(UFDTO ufDTO){
        UF uf = ufMapper.toEntity(ufDTO);
        ufRepository.save(uf);
        List<UFDTO> list = getAll(null, null, null, null);//retorna a lista atualizada
        return list;
    }

}
