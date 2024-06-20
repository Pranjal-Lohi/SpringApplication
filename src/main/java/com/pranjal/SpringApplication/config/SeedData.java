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
       


       List<Post> posts = postService.findAll();
       if (posts.isEmpty()){
            Post post01 = new Post();
            post01.setTitle("Simple and Delicious Pasta Recipe");
            post01.setBody("""
               <p>Ingredients:
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
Enjoy your delicious homemade pasta!</p> 
            
            """);
            post01.setAccount(account01);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("Quick and Easy Chai Tea Recipe");
            post02.setBody("""
                      
             <p>Ingredients:
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
Enjoy your homemade chai tea! Perfect for warming up on a cozy day.</p>
            
            """);
            
            post02.setAccount(account02);
            postService.save(post02);

            Post post03 = new Post();
            post03.setTitle("Beginner's Guide to Fresh Homemade Ravioli");
            post03.setBody("""
                        
            <h3><strong>Beginner's Guide to Fresh Homemade Ravioli</strong></h3>
            <p><a href="https://www.theclevercarrot.com/wp-content/uploads/2022/09/Homemade-ravioli_.jpg/"><img src="https://www.theclevercarrot.com/wp-content/uploads/2022/10/homemade-ravioli.jpg" alt="Fresh Homemade Ravioli" width="220"></a></p><p>&nbsp;</p><p>Making fresh homemade ravioli is a delightful culinary adventure. With a few basic ingredients and some tips, you can create delicious ravioli from scratch.</p>
            <p>Key steps include:</p>
            <ul>
                  <li><strong>Dough Preparation:</strong> Use a mix of flour, eggs, and water to form a smooth dough.</li>
                  <li><strong>Filling:</strong> Combine ingredients like ricotta, Parmesan, and spinach for a classic filling.</li>
                  <li><strong>Assembling:</strong> Roll out the dough, place the filling, and seal the edges carefully.</li>
                  <li><strong>Cooking:</strong> Boil the ravioli until they float, then serve with your favorite sauce.</li>
            </ul>
            <p>For detailed instructions and tips, visit the full guide on <a href="https://www.theclevercarrot.com/2022/10/beginners-guide-to-fresh-homemade-ravioli/">The Clever Carrot</a>.</p>
            
            """);
            post03.setAccount(account01);
            postService.save(post03);

            Post post04 = new Post();
            post04.setTitle("Easy One-Pot Pasta");
            post04.setBody("""
                        
            <h3><strong>Easy One-Pot Pasta</strong></h3>
            <p><a href="https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-6-768x1152.jpg/"><img src="https://frommybowl.com/wp-content/uploads/2022/10/One-Pot-Pasta.jpg" alt="Easy One-Pot Pasta" width="220"></a></p><p>&nbsp;</p><p>This easy one-pot pasta recipe is perfect for a quick and delicious meal. With minimal ingredients and cleanup, it’s ideal for busy weeknights.</p>
            <p>Key steps include:</p>
            <ul>
                  <li><strong>Sauté Aromatics:</strong> Begin by sautéing garlic and onion in olive oil until fragrant.</li>
                  <li><strong>Add Vegetables:</strong> Incorporate vegetables like bell peppers and zucchini, cooking until they soften.</li>
                  <li><strong>Combine Ingredients:</strong> Add pasta, canned tomatoes, vegetable broth, and seasonings to the pot.</li>
                  <li><strong>Cook:</strong> Bring to a boil, then simmer until the pasta is tender and most of the liquid is absorbed.</li>
                  <li><strong>Serve:</strong> Garnish with fresh basil or vegan parmesan, if desired.</li>
            </ul>
            <p>For detailed instructions and tips, visit the full recipe on <a href="https://frommybowl.com/easy-one-pot-pasta/">From My Bowl</a>.</p>
            
            """);
         
            
            post04.setAccount(account02);
            postService.save(post04);

            Post post05 = new Post();
            post05.setTitle("Fifth post");
            post05.setBody("""
               <h3><strong>Puran Poli Recipe</strong></h3>
               <p><a href="https://1.bp.blogspot.com/_e_gbLKOQYxo/Sb2lreJRB4I/AAAAAAAAAIY/xWrdpq8YYfM/s400/poli.jpg"><img src="https://1.bp.blogspot.com/_e_gbLKOQYxo/Sb2lreJRB4I/AAAAAAAAAIY/xWrdpq8YYfM/s400/poli.jpg" alt="Puran Poli" srcset="https://1.bp.blogspot.com/_e_gbLKOQYxo/Sb2lreJRB4I/AAAAAAAAAIY/xWrdpq8YYfM/s600/poli.jpg 1.5x, https://1.bp.blogspot.com/_e_gbLKOQYxo/Sb2lreJRB4I/AAAAAAAAAIY/xWrdpq8YYfM/s800/poli.jpg 2x" sizes="100vw" width="220"></a></p>
               <p>&nbsp;</p>
               <p><strong>Ingredients:</strong></p>
               <ul>
               <li>Chana dal - 1 cup</li>
               <li>Jaggery - 1 cup</li>
               <li>Cardamom powder - 1/2 tsp</li>
               <li>Nutmeg powder - a pinch</li>
               <li>Wheat flour - 1 cup</li>
               <li>All-purpose flour - 1/4 cup</li>
               <li>Salt - a pinch</li>
               <li>Oil - 2 tbsp</li>
               </ul>
               <p>&nbsp;</p>
               <p><strong>Method:</strong></p>
               <ol>
               <li>Cook chana dal in a pressure cooker until soft. Drain the water.</li>
               <li>In a pan, add cooked chana dal and jaggery. Cook until the mixture thickens.</li>
               <li>Add cardamom powder and nutmeg powder. Mix well and let it cool.</li>
               <li>In a bowl, mix wheat flour, all-purpose flour, salt, and oil. Add water to make a soft dough. Let it rest for 30 minutes.</li>
               <li>Make small balls of the dough and puran mixture. Stuff the puran mixture in the dough balls and roll them into flat discs.</li>
               <li>Cook on a hot griddle until golden brown on both sides. Serve hot with ghee.</li>
               </ol>
               <p>&nbsp;</p>
               <p>Enjoy the delicious <strong>Puran Poli</strong> during festive occasions or as a sweet treat anytime!</p>

            """);
            post05.setAccount(account01);
            postService.save(post05);

            Post post06 = new Post();
            post06.setTitle("Homemade Croissants");
            post06.setBody("""
                        
            <h3><strong>Homemade Croissants</strong></h3>
            <p><a href="https://sallysbakingaddiction.com/homemade-croissants/"><img src="https://cdn.sallysbakingaddiction.com/wp-content/uploads/2018/07/homemade-croissants.jpg" alt="Homemade Croissants" width="220"></a></p><p>&nbsp;</p><p>Baking homemade croissants is a rewarding process that results in delicious, flaky pastries. Here are the key steps:</p>
            <ul>
                  <li><strong>Preparing the Dough:</strong> Mix flour, sugar, salt, yeast, and milk to form the dough.</li>
                  <li><strong>Layering Butter:</strong> Roll out the dough, layer with butter, and fold multiple times to create flaky layers.</li>
                  <li><strong>Shaping:</strong> Cut the dough into triangles and roll them into crescent shapes.</li>
                  <li><strong>Proofing:</strong> Let the shaped croissants rise until puffy.</li>
                  <li><strong>Baking:</strong> Bake until golden brown and enjoy warm.</li>
            </ul>
            <p>For detailed instructions and tips, visit the full recipe on <a href="https://sallysbakingaddiction.com/homemade-croissants/">Sally's Baking Addiction</a>.</p>
            
            """);
   
            
            post06.setAccount(account02);
            postService.save(post06);


       }
        
    }
    
}
