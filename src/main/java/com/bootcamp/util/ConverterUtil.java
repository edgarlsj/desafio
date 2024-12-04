package com.bootcamp.util;

import com.bootcamp.exceptions.DesafioException;
import lombok.Data;

@Data

public class ConverterUtil {

    public static Long convertStringToLong(String value) {
        if (value == null) {
            return null;
        }
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw  new DesafioException("Não foi possivel consultar  no banco de dados. Motivo: o Valor do campo status ");
        }
    }

    public static Integer convertStringToInteger(String value) {
        if (value == null) {
            return null;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw  new DesafioException("Não foi possivel consultar  no banco de dados. Motivo: o Valor do campo status ");
        }
    }
}
