package com.bootcamp.services;

import com.bootcamp.dto.MunicipioDTO;
import com.bootcamp.entities.Municipio;
import com.bootcamp.entities.UF;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.repositories.MunicipioRepository;
import com.bootcamp.mapper.MunicipioMapper;
import com.bootcamp.repositories.UFRepository;
import com.bootcamp.util.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;
    @Autowired
    private MunicipioMapper municipioMapper;
    @Autowired
    private UFRepository UFRepository;

    public List<MunicipioDTO> getAll(Long codigoMunicipio, Long codigoUF, String nome, Integer status) {
        List<Municipio> municipios = municipioRepository.findAll().stream()
                .filter(m -> (codigoMunicipio == null || m.getCodigoMunicipio().equals(codigoMunicipio)) &&
                        (codigoUF == null || m.getUf().getCodigoUF().equals(codigoUF)) &&
                        (nome == null || m.getNome().equalsIgnoreCase(nome)) &&
                        (status == null || m.getStatus() == status))
                .collect(Collectors.toList());

        return municipios.stream()
                .map(municipioMapper::toDTO)
                .collect(Collectors.toList());

    }


    public List<MunicipioDTO> findByFilters(Optional<Long> codigoMunicipio, Optional<Long> codigoUF, Optional<String> nome, Optional<Integer> status) {
        List<Municipio> municipios = municipioRepository.findByFilters(codigoMunicipio, codigoUF, nome, status);

        return municipios.stream()
                .map(municipio -> new MunicipioDTO(
                        municipio.getCodigoMunicipio(),
                        municipio.getUf().getCodigoUF(),
                        municipio.getNome(),
                        municipio.getStatus()))
                .sorted((o1, o2) -> -o1.getCodigoMunicipio().compareTo(o2.getCodigoMunicipio()))
                .collect(Collectors.toList());
    }




















//    @Transactional
//    public Object getByCodigoMunicipio(Long codigoMunicipio) {
//        Municipio municipio = municipioRepository.findById(codigoMunicipio).orElse(null);
//
//        if (municipio == null) {
//            return new ArrayList<>();
//    }
//        return municipioMapper.toDTO(municipio);
//    }
    @Transactional
    public List<MunicipioDTO> createMunicipio(MunicipioDTO municipioDTO) {
//        Validações
        ValidateUtils.validateStatus(municipioDTO.getStatus());
        ValidateUtils.validateNome(municipioDTO.getNome());


        // Remove espaços do nome
        municipioDTO.setNome(municipioDTO.getNome().trim());

      //Valida se já existe um registro com o mesmo nome ou sigla
        if (municipioRepository.existsByNomeAndUf_CodigoUF(municipioDTO.getNome(), municipioDTO.getCodigoUF())) {
            throw new DesafioException("Não foi possível incluir municipio no banco de dados. Motivo: município com nome "+municipioDTO.getNome()+" e código da UF "+municipioDTO.getCodigoUF()+" já existe.");
        }
        if (!UFRepository.existsByCodigoUF(municipioDTO.getCodigoUF())) {
            throw new DesafioException("Não foi possível incluir municipio no banco de dados. Motivo: código do UF não encontrado.");
        }


        Municipio municipio = municipioMapper.toEntity(municipioDTO);

        municipio.setCodigoMunicipio(null);


         municipioRepository.save(municipio);
         List<MunicipioDTO> municipios = getAll(null, null, null, null);//retona todos os municipios
        return municipios;
    }

    @Transactional
    public List<MunicipioDTO> updateMunicipio(MunicipioDTO municipioDTO) {
        ValidateUtils.validateNome(municipioDTO.getNome());
        ValidateUtils.validateStatus(municipioDTO.getStatus());

        if (!municipioRepository.existsByCodigoMunicipio(municipioDTO.getCodigoMunicipio())) {
            throw new DesafioException("Não foi possível atualizar municipio no banco de dados. Motivo: código do municipio "+municipioDTO.getCodigoMunicipio()+" não foi encontrado!.");
        }
        if (!UFRepository.existsByCodigoUF(municipioDTO.getCodigoUF())) {
            throw new DesafioException("Não foi possível atualizar municipio no banco de dados. Motivo: código do UF "+municipioDTO.getCodigoUF()+" não foi encontrado!.");
        }



        Municipio municipio = municipioMapper.toEntity(municipioDTO);
        municipioRepository.save(municipio);
        List<MunicipioDTO> municipios = getAll(null, null, null, null);//retona todos os municipios
        return municipios;
    }



}
