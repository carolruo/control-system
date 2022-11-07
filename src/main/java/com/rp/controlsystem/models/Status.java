package com.rp.controlsystem.models;

public enum Status {
    PENDING("Pendente"), 
    ACTIVE("Em progresso"), 
    INTERRUPTED("Interrompido"), 
    CANCELED("Cancelado"), 
    DONE("Concluído");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
