package ru.itis.readl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.readl.models.Account;
import ru.itis.readl.models.Book;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);

    boolean existsAccountByEmail(String email);

    boolean existsAccountByIdAndFavouriteBooksContains(Long accountId, Book book);

}
