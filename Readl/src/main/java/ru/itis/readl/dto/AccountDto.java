package ru.itis.readl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.readl.models.Account;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    private Long id;

    private String nickname;

    public static AccountDto from(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .nickname(account.getNickname())
                .build();
    }
}
