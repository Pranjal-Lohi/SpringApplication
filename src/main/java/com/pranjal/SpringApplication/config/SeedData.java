package com.pranjal.SpringApplication.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pranjal.SpringApplication.models.Account;
import com.pranjal.SpringApplication.models.Authority;
import com.pranjal.SpringApplication.models.Post;
import com.pranjal.SpringApplication.services.AccountService;
import com.pranjal.SpringApplication.services.AuthorityService;
import com.pranjal.SpringApplication.services.PostService;
import com.pranjal.SpringApplication.util.constants.Privillages;
import com.pranjal.SpringApplication.util.constants.Roles;

@Component
public class SeedData implements CommandLineRunner{

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {

       for(Privillages auth: Privillages.values()){
            Authority authority = new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivillage());
            authorityService.save(authority);

       }
        
       Account account01 = new Account();
       Account account02 = new Account();
       Account account03 = new Account();
       Account account04 = new Account();

       account01.setEmail("user@user.com");
       account01.setPassword("pass987");
       account01.setFirstname("User");
       account01.setLastname("lastname");
       account01.setAge(25);
       account01.setDate_of_birth(LocalDate.parse("1990-01-01"));
       account01.setGender("Male");



       account02.setEmail("admin@admin.com");
       account02.setPassword("pass987");
       account02.setFirstname("Admin");
       account02.setLastname("lastname");
       account02.setRole(Roles.ADMIN.getRole());
       account02.setAge(25);
       account02.setDate_of_birth(LocalDate.parse("1990-01-01"));
       account02.setGender("Famale");

       account03.setEmail("editor@editor.com");
       account03.setPassword("pass987");
       account03.setFirstname("Editor");
       account03.setLastname("lastname");
       account03.setRole(Roles.EDITOR.getRole());
       account03.setAge(55);
       account03.setDate_of_birth(LocalDate.parse("1975-01-01"));
       account03.setGender("Male");

       account04.setEmail("super_editor@editor.com");
       account04.setPassword("pass987");
       account04.setFirstname("Editor");
       account04.setLastname("lastname");
       account04.setRole(Roles.EDITOR.getRole());
       account04.setAge(40);
       account04.setDate_of_birth(LocalDate.parse("1980-01-01"));
       account04.setGender("Female");

       
       Set<Authority> authorities = new HashSet<>();
       authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
       authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
       account04.setAuthorities(authorities);

       accountService.save(account01);
       accountService.save(account02);
       accountService.save(account03);
       accountService.save(account04);
       


       List<Post> posts = postService.getAll();
       if (posts.size() == 0){
            Post post01 = new Post();
            post01.setTitle("Simple and Delicious Pasta Recipe");
            post01.setBody("""
               Ingredients:
200g of your favorite pasta (spaghetti, penne, fusilli, etc.)
2 tablespoons olive oil
3 cloves garlic, minced
1 can (400g) diced tomatoes
1 teaspoon dried oregano
1 teaspoon dried basil
Salt and pepper to taste
Freshly grated Parmesan cheese (optional)
Fresh basil leaves for garnish (optional)
Instructions:
Boil the Pasta:

Fill a large pot with water, add a pinch of salt, and bring it to a boil.
Add the pasta and cook according to the package instructions until al dente (firm to the bite).
Drain the pasta and set aside.
Prepare the Sauce:

In a large skillet, heat the olive oil over medium heat.
Add the minced garlic and sauté for about 1 minute until fragrant, but not browned.
Add the diced tomatoes (with their juice) to the skillet.
Stir in the dried oregano, dried basil, salt, and pepper.
Let the sauce simmer for about 10-15 minutes, stirring occasionally, until it thickens slightly.
Combine Pasta and Sauce:

Add the cooked pasta to the skillet with the sauce.
Toss everything together until the pasta is well coated with the sauce.
Adjust seasoning with salt and pepper, if needed.
Serve:

Serve the pasta hot, topped with freshly grated Parmesan cheese if desired.
Garnish with fresh basil leaves for an extra touch of flavor and color.
Tips:
For a richer flavor, you can add a splash of red wine to the sauce while it simmers.
Feel free to add in other ingredients like sautéed vegetables, cooked chicken, or shrimp to customize your pasta dish.
Enjoy your delicious homemade pasta!   
            
            """);
            post01.setAccount(account01);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("Quick and Easy Chai Tea Recipe");
            post02.setBody("""
                      
             Ingredients:
2 cups water
1 cup milk (dairy or plant-based)
2 black tea bags or 2 tablespoons loose black tea
2-3 tablespoons sugar or honey (adjust to taste)
1 cinnamon stick
4 whole cloves
2 cardamom pods, crushed
1-2 slices fresh ginger
1/2 teaspoon black peppercorns
1/2 teaspoon ground nutmeg (optional)
Instructions:
Boil the Spices:

In a medium saucepan, combine water, cinnamon stick, cloves, crushed cardamom pods, ginger, and black peppercorns.
Bring to a boil over medium heat, then reduce to a simmer. Let the spices simmer for about 5 minutes.
Brew the Tea:

Add the black tea (tea bags or loose tea) to the saucepan.
Simmer for another 3-5 minutes, depending on how strong you like your tea.
Add Milk and Sweetener:

Stir in the milk and sugar or honey.
Increase the heat slightly and bring the mixture to a gentle boil.
Simmer for 2-3 more minutes until the tea is well blended and hot.
Strain and Serve:

Strain the chai tea into cups using a fine mesh strainer.
Serve hot, optionally garnished with a sprinkle of ground nutmeg.
Tips:
For a spicier chai, increase the amount of ginger and black pepper.
You can prepare a larger batch of the spice mix and store it in an airtight container for quick chai anytime.
Enjoy your homemade chai tea! Perfect for warming up on a cozy day.
            
            """);
            
            post02.setAccount(account02);
            postService.save(post02);

       }
        
    }
    
}
