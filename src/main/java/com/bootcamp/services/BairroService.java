package com.bootcamp.services;

import com.bootcamp.dto.BairroDTO;
import com.bootcamp.entities.Bairro;
import com.bootcamp.exceptions.DesafioException;
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
        Bairro bairro = bairroRepository.findById(codigoBairro).orElseThrow(() -> new DesafioException("Não foi possível consultar bairro no banco de dados."));
        return bairroMapper.toDto(bairro);
    }

    @Transactional
    public List<BairroDTO> createBairro(BairroDTO bairroDTO) {
        Bairro bairro = bairroMapper.toEntity(bairroDTO);
        bairroRepository.save(bairro);
        List<BairroDTO> bairrosDTO = getAll(null, null, null);//retona todos os bairros
        return bairrosDTO;
    }

    @Transactional
    public List<BairroDTO> updateBairro(BairroDTO bairroDTO) {
        Bairro bairro = bairroMapper.toEntity(bairroDTO);
        bairroRepository.save(bairro);
        List<BairroDTO> bairrosDTO = getAll(null, null, null);//retona todos os bairros
        return bairrosDTO;
    }



}
