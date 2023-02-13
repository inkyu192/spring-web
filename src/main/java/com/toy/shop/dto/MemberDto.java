package com.toy.shop.dto;

import com.toy.shop.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

public class MemberDto {

    @Getter
    public static class Save {

        @NotEmpty
        private String name;

        @NotEmpty
        private String city;

        @NotEmpty
        private String street;

        @NotEmpty
        private String zipcode;
    }

    @Getter
    public static class Update {

        private String name;

        private String city;

        private String street;

        private String zipcode;
    }

    @Getter
    public static class Response {

        private Long id;
        private String name;
        private String city;
        private String street;
        private String zipcode;

        public Response(Member member) {
            this.id = member.getId();
            this.name = member.getName();
            this.city = member.getAddress().getCity();
            this.street = member.getAddress().getStreet();
            this.zipcode = member.getAddress().getZipcode();
        }
    }
}
