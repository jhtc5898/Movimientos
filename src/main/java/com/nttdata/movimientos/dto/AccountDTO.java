package com.nttdata.movimientos.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AccountDTO {

    @Data
    public static class createAccount {

        @NotNull
        private String clientidentification;

        @NotNull
        @Size(min = 1, max = 1)
        @Pattern(regexp = "[AC]")
        private String typeAccount;
    }

    @Data
    public static class editAccount {

        @NotNull
        private String clientidentification;

        @NotNull
        private Long idAccount;

        @NotNull
        @Size(min = 1, max = 1)
        @Pattern(regexp = "[AC]")
        private String typeAccount;
    }

    @Data
    public static class infoAccount {

        @NotNull
        private String clientidentification;

        @NotNull
        private Long idAccount;

    }

    @Data
    public static class deleteAccount {

        @NotNull
        private String clientidentification;

        @NotNull
        private Long idAccount;


    }
}
