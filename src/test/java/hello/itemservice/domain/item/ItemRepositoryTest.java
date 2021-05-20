package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item item = new Item("itemA",10000,10);
        //when
        Item saveItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(item.getId());

        assertThat(findItem).isEqualTo(saveItem);
    }
    @Test
    void findAll(){
        //given
        Item item1 = new Item("item1",10000,10);
        Item item2 = new Item("item2",20000,20);

        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> items = itemRepository.findAll();
        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1,item2);
    }
    @Test
    void updateItem(){
        //given
        Item item = new Item("item1", 10000, 10);

        Item savedItem = itemRepository.save(item);
        Long findId = savedItem.getId();
        //when
        Item updatedItem = new Item("item2", 20000, 20);
        itemRepository.update(findId,updatedItem);
        //then
        Item findItem = itemRepository.findById(findId);
        assertThat(findItem.getItemName()).isEqualTo(updatedItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updatedItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updatedItem.getQuantity());
    }
}