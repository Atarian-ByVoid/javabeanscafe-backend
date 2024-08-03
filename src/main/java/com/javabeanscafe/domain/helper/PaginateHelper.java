package com.javabeanscafe.domain.helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import com.javabeanscafe.application.exception.CustomException;

public class PaginateHelper {

    static final public String PAGE_MIN_REQUEST = "0";
    static final public String SIZE_MIN_REQUEST = "10";
    static final public int PAGE_MIN_DEFAULT = 0;
    static final public int SIZE_MIN_DEFAULT = 1;
    static final public int SIZE_MAX_DEFAULT = 100;

    /**
     * Valida o número da página a ser utilizada na paginação, com base em
     * parâmetros de entrada e constantes default
     * 
     * @param page    int - Número da página requisitada
     * @param pageMax Integer/null - Número limite de página
     * 
     * @return int - Número para página validado
     */
    static public int validarPage(int page, Integer pageMax) {
        if (page < 0) {
            return PAGE_MIN_DEFAULT;
        }

        if (pageMax != null && page > pageMax) {
            return pageMax;
        }

        return page;
    }

    /**
     * Valida a quantidade de recursos que serão retornados pela paginação,
     * com base em parâmetros de entrada e constantes default
     * 
     * @param size    int - Quantidade de recursos desejados por página
     * @param sizeMin Integer/null - Quantidade mínima de recursos por página
     * @param sizeMax Integer/null - Quantidade máxima de recursos por página
     * 
     * @return int - Número para quantidade validado
     */
    static public int validarSize(int size, Integer sizeMin, Integer sizeMax) {
        if (sizeMin == null) {
            sizeMin = SIZE_MIN_DEFAULT;
        }

        if (sizeMax == null) {
            sizeMax = SIZE_MAX_DEFAULT;
        }

        if (size < sizeMin) {
            return sizeMin;
        }

        if (size > sizeMax) {
            return sizeMax;
        }

        return size;
    }

    /**
     * Cria um objeto Sort para ordenação, com base nas propriedades e
     * ordenações recebidas
     * 
     * @param sortProperties List<String> - Lista de propriedades
     * @param sortOrders     List<String> - Lista de tipos de ordenação (asc|desc)
     * 
     * @return Sort - Objeto de Ordenação
     */
    static public Sort buildSort(
            List<String> sortProperties,
            List<String> sortOrders) {
        if (null == sortProperties) {
            sortProperties = new ArrayList<>();
        }

        if (null == sortOrders) {
            sortOrders = new ArrayList<>();
        }

        if (sortProperties.isEmpty()) {
            return Sort.unsorted();
        }

        List<Sort.Order> ordenacoes = new ArrayList<>();

        for (int i = 0; i < sortProperties.size(); i++) {
            String campo = sortProperties.get(i);
            String ordem = i < sortOrders.size() ? sortOrders.get(i) : "asc";

            Sort.Direction direcao = ordem.equalsIgnoreCase("desc")
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC;

            ordenacoes.add(Sort.Order.by(campo).with(direcao));
        }

        return Sort.by(ordenacoes);
    }

    /**
     * Cria um objeto Pageable para a paginação em Query
     * 
     * @param page int - Página a ser buscada pela paginação
     * @param size in - Quantidade de recursos a ser retornada por página
     * @param sort Sort - Objeto de ordenação
     * 
     * @return Pageable - Objeto de paginação
     */
    static public Pageable buildPageable(
            int page,
            int size,
            Sort sort) {
        return PageRequest.of(page, size, sort);
    }

    /**
     * Valida se os parâmetros desejados para ordenação existem como atributos
     * na Classe designada
     * 
     * @param <T>            T - Tipo da Classe a ser verificada
     * @param sortProperties List<String> - Lista de parâmetros para ordenação
     * @param entityClass    Class<T> - Classe a ser verificada
     * 
     * @throws NoSuchFieldException - Se o parâmetro não existir como atributo
     */
    static public <T> void validarParametrosComClass(
            List<String> sortProperties,
            Class<T> entityClass) throws NoSuchFieldException {
        if (null == sortProperties) {
            sortProperties = new ArrayList<>();
        }
        for (String parametro : sortProperties) {
            try {
                Field campo = entityClass.getDeclaredField(parametro);
            } catch (NoSuchFieldException e) {
                Class<?> superClass = entityClass.getSuperclass();

                if (superClass != null && !superClass.equals(Object.class)) {
                    recursiveCheckSuperClassField(parametro, superClass);
                } else {
                    throw e;
                }
            }
        }
    }

    /**
     * Realiza recursividade sobre superclasses para determinar se um parâmetro
     * existe em alguma delas
     * 
     * @param fieldName  String - Nome do parâmetro/campo a ser verificado
     * @param superClass Class<?> - Classe a ser verificado a existência do
     *                   parâmetro
     * 
     * @throws NoSuchFieldException - Se o parâmetro a ser buscado não existir
     */
    static private void recursiveCheckSuperClassField(
            String fieldName,
            Class<?> superClass) throws NoSuchFieldException {
        try {
            Field campo = superClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            if (superClass != null && !superClass.equals(Object.class)) {
                recursiveCheckSuperClassField(
                        fieldName,
                        superClass.getSuperclass());
            } else {
                throw e;
            }
        }
    }

    /**
     * Verifica se a(s) propriedade(s) a serem ordenadas existem na classe a
     * ser pesquisada
     * 
     * @param <T>            - Tipo da classe que será verificada
     * @param sortProperties List<String> - Lista de nomes de propriedades a
     *                       serem verificadas
     * @param entityClass    Class<T> - Classe a ser verificada
     */
    static public <T> void validarSortProperties(
            List<String> sortProperties,
            Class<T> entityClass) {
        try {
            PaginateHelper.validarParametrosComClass(
                    sortProperties,
                    entityClass);
        } catch (NoSuchFieldException e) {
            Map<String, Object> parametros = new HashMap<>();
            String mensagem = "Parametro inválido para ordenação";
            parametros.put("sortProperties", sortProperties);
            parametros.put("invalid", e.getMessage());

            throw new CustomException(
                    HttpStatus.BAD_REQUEST,
                    mensagem,
                    "Bad request");
        }
    }
}
