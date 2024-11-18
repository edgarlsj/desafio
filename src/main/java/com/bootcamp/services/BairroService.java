package com.bootcamp.services;

import com.bootcamp.dto.BairroDTO;
import com.bootcamp.entities.Bairro;
import com.bootcamp.repositories.BairroRepository;
import com.bootcamp.mapper.BairroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BairroService {
    @Autowired
    public BairroRepository bairroRepository;

    @Autowired
    public BairroMapper bairroMapper;


        @Transactional
        public List<BairroDTO> getAll(Long codigoMunicipio, String nome, Integer status) {
            List<Bairro> bairros = bairroRepository.findAll().stream()
                    .filter(b -> (codigoMunicipio == null || b.getMunicipio().getCodigoMunicipio().equals(codigoMunicipio)) &&
                            (nome == null || b.getNome().equalsIgnoreCase(nome)) &&
                            (status == null || b.getStatus() == status))
                    .collect(Collectors.toList());
            return bairros.stream()
                    .map(bairroMapper::toDto)
                    .collect(Collectors.toList());

    }

    @Transactional
    public BairroDTO getByCodigoBairro(Long codigoBairro) {
        Bairro bairro = bairroRepository.findById(codigoBairro).orElse(null);
        return bairroMapper.toDto(bairro);
    }



}
