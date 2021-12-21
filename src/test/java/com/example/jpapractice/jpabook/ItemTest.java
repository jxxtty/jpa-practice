package com.example.jpapractice.jpabook;

import com.example.jpapractice.jpabook.domain.Book;
import com.example.jpapractice.jpabook.domain.Cloth;
import com.example.jpapractice.jpabook.domain.Item;
import com.example.jpapractice.jpabook.domain.Movie;
import com.example.jpapractice.jpabook.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


//@DataJpaTest
//@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
public class ItemTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void 옷_엔티티생성() {
        // given
        Cloth newCloth = new Cloth();
        newCloth.setName("아이템1_옷1");
        newCloth.setPrice(10000);
        newCloth.setBrandName("ACNE");
        newCloth.setCategory("top");

        // when
        Cloth savedCloth = itemRepository.save(newCloth);

        // then
        assertThat(savedCloth.getCategory()).isEqualTo("top");
    }

    @Test
    void 책_엔티티생성(){
        Book newBook = new Book();
        newBook.setName("아이템2_책1");
        newBook.setPrice(1000);
        newBook.setAuthor("김영한");
        newBook.setIsbn("YHisbn");

        Book savedBook = itemRepository.save(newBook);

        assertThat(savedBook.getAuthor()).isEqualTo("김영한");

    }

    @Test
    void 영화_엔티티생성(){
        Movie newMovie = new Movie();
        newMovie.setName("아이템3_영화1");
        newMovie.setPrice(13000);
        newMovie.setActor("톰홀랜드");
        newMovie.setDirector("존왓츠");

        Movie savedMovie = itemRepository.save(newMovie);

        assertThat(savedMovie.getActor()).isEqualTo("톰홀랜드");
    }
}
