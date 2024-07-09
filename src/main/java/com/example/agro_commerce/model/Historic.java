    package com.example.agro_commerce.model;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.security.PrivateKey;
    import java.time.LocalDate;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor

    public class Historic {
        private Integer historicId;
        private Integer Reservation;
        private Integer userId;
        private Integer sellerId;
        private LocalDate boughtIn;

        @Override
        public String toString() {
            return "Historic{" +
                    "historicId=" + historicId +
                    ", userId=" + userId +
                    ", sellerId=" + sellerId +
                    ", bought_in=" + boughtIn +
                    '}';
        }

    }
