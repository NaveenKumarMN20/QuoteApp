package org.im.quotes.controller;

import org.im.quotes.model.QuotesModel;
import org.im.quotes.repository.QuotesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class QuotesController {

    private static final Logger log = LoggerFactory.getLogger(QuotesController.class);

    @Autowired
    private QuotesModel quotesModel;

    @Autowired
    private List<QuotesModel> quotesModelList;

    @Autowired
    private QuotesRepository quotesRepository;

    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @PostMapping("/addQuote")
    public ResponseEntity<QuotesModel> addQuotes(@RequestBody QuotesModel quotes) {

        quotesModel = quotesRepository.save(quotes);
        log.info("Saved quote="+quotesModel.toString());
        if (quotesModel != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(quotesModel);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping("/allQuotes")
    public ResponseEntity<List<QuotesModel>> getAllQuotes() {

        quotesModelList = quotesRepository.findAll();
        if (quotesModelList.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(quotesModelList);
    }

    @GetMapping("/author={author}")
    public ResponseEntity<List<QuotesModel>> getByAuthor(@PathVariable String author) {

        quotesModelList = quotesRepository.findByAuthor(author);
        if (quotesModelList.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(quotesModelList);
    }

    @GetMapping("/word={word}")
    public ResponseEntity<List<QuotesModel>> getByWord(@PathVariable String word) {

        quotesModelList = quotesRepository.findByQuoteContainsAllIgnoreCase(word);
        if (quotesModelList.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(quotesModelList);
    }

}
