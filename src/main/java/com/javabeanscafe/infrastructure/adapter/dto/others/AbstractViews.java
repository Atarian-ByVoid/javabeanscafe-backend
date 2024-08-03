package com.javabeanscafe.infrastructure.adapter.dto.others;

public abstract class AbstractViews {
    public interface ComDataCriacao {
    }

    public interface ComDataAlteracao {
    }

    public interface ComDataExclusao {
    }

    public interface ComTimestamps extends
            AbstractViews.ComDataCriacao,
            AbstractViews.ComDataAlteracao,
            AbstractViews.ComDataExclusao {
    }

    public interface ComIdentificador {
    }

    public interface Paginate {
    }
}