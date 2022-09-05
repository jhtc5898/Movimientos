package com.nttdata.movimientos.dto;


import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class TransactionsDTO {

    @Data
    public static class createTransactions {

        @NotNull
        private String clientidentification;

        @NotNull
        private Long idAccount;

        @NotNull
        @Size(min = 1, max = 1)
        @Pattern(regexp = "[AC]")
        private String typeAccount;

        @NotNull
        @Size(min = 1, max = 1)
        @Pattern(regexp = "[+-]")
        private String typeTransaccion;

        @NotNull
        @Range(min = 1, max = 5000)
        private Long transaction;

    }


    @Data
    public static class listTransactions {

        @NotNull
        private String clientidentification;

        @NotNull
        @Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")
        private String dateInit;

        @NotNull
        @Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")
        private String dateFin;

    }

    @Data
    public static class deleteTransactions {

        @NotNull
        private Long idTransactions;

    }

}
