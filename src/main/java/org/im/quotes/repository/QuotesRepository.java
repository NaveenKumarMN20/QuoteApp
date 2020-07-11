package org.im.quotes.repository;

import org.im.quotes.model.QuotesModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotesRepository extends MongoRepository<QuotesModel,String> {

    List<QuotesModel> findByAuthor(String author);

    List<QuotesModel> findByQuoteContainsAllIgnoreCase(String author);

}
