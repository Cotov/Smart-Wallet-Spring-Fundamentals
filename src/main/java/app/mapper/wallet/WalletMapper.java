package app.mapper.wallet;

import app.model.dto.wallet.WalletDto;
import app.model.entity.wallet.Wallet;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WalletMapper {

    public static WalletDto toDto(Wallet wallet){
        return wallet == null ? null 
        : WalletDto.builder()
        .id(wallet.getId())
        //todo should be dto
        .owner(wallet.getOwner())
        .status(wallet.getStatus())
        .balance(wallet.getBalance())
        .currency(wallet.getCurrency())
        .createdOn(wallet.getCreatedOn())
        .updatedOn(wallet.getUpdatedOn())
        .build();
    }

}
