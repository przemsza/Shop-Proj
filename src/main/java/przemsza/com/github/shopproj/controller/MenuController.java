package przemsza.com.github.shopproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import przemsza.com.github.shopproj.model.item.Item;
import przemsza.com.github.shopproj.model.item.ItemRepository;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private ItemRepository itemRepository;

    @Autowired
    public MenuController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String getMenu(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "menu";
    }

}
