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



       account02.setEmail("pranjallohi123@gmail.com");
       account02.setPassword("pass987");
       account02.setFirstname("Admin");
       account02.setLastname("lastname");
       account02.setRole(Roles.ADMIN.getRole());
       account02.setAge(25);
       account02.setDate_of_birth(LocalDate.parse("1990-01-01"));
       account02.setGender("Female");

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
            post01.setTitle("How To Make Chai Tea");
            post01.setBody("""
               	<p>A truly <strong>authentic Indian Chai tea recipe</strong>, as made for me by my lovely Indian mother-in-law. Tea the Indian way is rich and milky, deeply coloured, steaming hot, flavourful and sweet. You’ll love this homemade masala chai from scratch with warming spices like whole cinnamon.</p><p><img src="https://foodess.com/wp-content/uploads/2016/11/authentic-chai-tea-recipe-1.jpg"  style="width: 200px; height: auto; alt="homemade chai tea in a pot with indian spices" srcset="https://foodess.com/wp-content/uploads/2016/11/authentic-chai-tea-recipe-1.jpg 790w, https://foodess.com/wp-content/uploads/2016/11/authentic-chai-tea-recipe-1-240x300.jpg 240w, https://foodess.com/wp-content/uploads/2016/11/authentic-chai-tea-recipe-1-768x960.jpg 768w, https://foodess.com/wp-content/uploads/2016/11/authentic-chai-tea-recipe-1-300x375.jpg 300w, https://foodess.com/wp-content/uploads/2016/11/authentic-chai-tea-recipe-1-220x275.jpg 220w, https://foodess.com/wp-content/uploads/2016/11/authentic-chai-tea-recipe-1-539x673.jpg 539w, https://foodess.com/wp-content/uploads/2016/11/authentic-chai-tea-recipe-1-182x228.jpg 182w" sizes="100vw" width="790"></p><h2>What is Chai Tea Made of?</h2><p>What I love so much about it is that the tea is actually brewed in milk and water in a saucepan on the stove.</p><p>Because of this, it stays piping hot until ready to be drunk, rather than cooling down as it steeps in a teapot.</p><p>Authentic chai tea made from:</p><ul><li>Water</li><li>Milk (in a ratio of roughly one part milk to two parts water</li><li>Lots of tea leaves or tea bags for strong infusion</li><li>Plenty of sugar</li><li>Optional addition of spices</li></ul><h2>How to Make Chai Tea at Home</h2><p>Despite <strong>what Starbucks would have you believe</strong> with their chai tea lattes, the <strong>real homemade cha</strong>i tea recipe doesn’t start with a syrup and it’s not super heavily spiced. &nbsp;</p><p>A “recipe” is not really necessary; it’s really <i>the method</i> that matters.</p><p>The most important key for flavour is to make really <strong>good strong tea</strong> without letting it get bitter from oversteeping.</p><p><img src="https://foodess.com/wp-content/uploads/2022/10/authentic-indian-chai-3-683x1024.jpg" style="width: 200px; height: auto; alt="adding the black tea to the milk mixture" srcset="https://foodess.com/wp-content/uploads/2022/10/authentic-indian-chai-3-683x1024.jpg 683w, https://foodess.com/wp-content/uploads/2022/10/authentic-indian-chai-3-200x300.jpg 200w, https://foodess.com/wp-content/uploads/2022/10/authentic-indian-chai-3-768x1152.jpg 768w, https://foodess.com/wp-content/uploads/2022/10/authentic-indian-chai-3-1024x1536.jpg 1024w, https://foodess.com/wp-content/uploads/2022/10/authentic-indian-chai-3-152x228.jpg 152w" sizes="100vw" width="683"></p><p><strong>Here’s how to make really good cup of chai:</strong></p><ol><li>Bring <strong>water, milk and spices</strong> to a simmer in a small saucepan on the stove (adding the spices from the start gives them plenty of time to infuse).</li><li>Reduce heat and add in <strong>black tea</strong> to brew.</li><li>Wait for tea to <strong>steep</strong> (don’t boil it at this point or the tea releases too many tannins – those bitter compounds that make your mouth feel dry).</li><li><strong>Strain</strong> the warm spiced mixture into a mug; this will hold back the whole spices and tea leaves.</li><li>Stir <strong>sugar</strong> into your chai and sweeten to taste.</li></ol><p>Scale the recipe measurements below to the number of servings you need.</p>
            """);
            post01.setAccount(account01);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("Easy One-Pot Pasta");
            post02.setBody("""
                      
             <p><i>This Easy One-Pot Pasta is the perfect weeknight dinner – uncooked pasta, veggies, and sauce combine and cook together in one pot in only 20 minutes!</i></p><p>One-Pot Pasta. Also known as the&nbsp;<strong>ultimate cozy weeknight dinner&nbsp;</strong>because it’s quick, easy,&nbsp;<i>satisfying</i>, and easy to pack with veggies. Plus, cleanup is a breeze 😉 Let’s get into it…</p><p><img src="https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-6.jpg" style="width: 150px; height: auto; alt="close up photo of cooked one pot pasta with wooden spoon" srcset="https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-6.jpg 1200w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-6-200x300.jpg 200w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-6-768x1152.jpg 768w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-6-683x1024.jpg 683w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-6-1170x1755.jpg 1170w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-6-585x878.jpg 585w" sizes="100vw" width="4000"></p><p>&nbsp;</p><h3><strong>INGREDIENTS FOR ONE-POT PASTA</strong></h3><p>This <strong>20-minute meal</strong> comes together with only a few key (and affordable!) ingredients:</p><ul><li><strong>Dry Pasta</strong> (yes, <i>un</i>cooked)</li><li><strong>Pasta Sauce&nbsp;</strong></li><li><strong>Water&nbsp;</strong></li><li><strong>Onion &amp; Garlic</strong></li><li><i><strong>Veggies!&nbsp;</strong></i></li></ul><p>I decided to use a combination of tomatoes, mushrooms, zucchini, and spinach for my pasta, but you can really use whatever you have on hand! Take some away, add extra ones in – you do you, boo.</p><p><img src="https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-1.jpg" style="width: 150px; height: auto; alt="ingredients for one pot pasta arranged in cooking pan before cooking on marble background" srcset="https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-1.jpg 1200w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-1-200x300.jpg 200w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-1-768x1152.jpg 768w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-1-683x1024.jpg 683w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-1-1170x1755.jpg 1170w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-1-585x878.jpg 585w" sizes="100vw" width="3693"></p><h3><strong>HOW TO MAKE ONE-POT PASTA</strong></h3><p>The <i>secret to success</i> in one pot pasta is cooking the dry pasta with&nbsp;<i>just the right amount of water</i> in order for the noodles to cook completely – without leaving any extra liquid behind. Luckily I’ve done all the work for you, and all you need to do is follow the (very, very easy) recipe!</p><ol><li>Pour the <strong>pasta</strong> on the bottom of the pot or pan</li><li><strong>Add</strong> the remaining ingredients</li><li>Bring the mixture to a boil, then <strong>simmer</strong> until the pasta is al-dente</li><li>Serve and <strong>enjoy</strong>!</li></ol><p><img src="https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-3.jpg" style="width: 150px; height: auto; alt="pan of cooked one pot pasta with red sauce on marble background" srcset="https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-3.jpg 1200w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-3-200x300.jpg 200w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-3-768x1152.jpg 768w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-3-683x1024.jpg 683w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-3-1170x1755.jpg 1170w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-3-585x877.jpg 585w" sizes="100vw" width="3773"></p><h3><strong>THE BEST NOODLES FOR ONE-POT PASTA</strong></h3><p>This One Pot Pasta can be made with both gluten-free and gluten-<i>full</i> noodles, but I would recommend&nbsp;<strong>using shorter shapes over longer ones, like spaghetti</strong>. Longer noodles are harder to stir and tend to stick together more while they cook. Shorter noodles can be stirred immediately (which is important) and and often as needed – no need to wait until they soften.</p><p><i>If you do opt for gluten-free</i> noodles, I have found that brown rice pasta seems to work the best, followed by other corn + grain blends. Bean and lentil-based pastas fall apart in one pot recipes&nbsp;<i>and</i> release more starch, which leads to a gummier final product.</p><p><img src="https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-7.jpg" style="width: 150px; height: auto; alt="bowl of cooked one pot pasta next to pan of pasta on marble background" srcset="https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-7.jpg 1200w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-7-200x300.jpg 200w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-7-768x1152.jpg 768w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-7-683x1024.jpg 683w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-7-1170x1755.jpg 1170w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl-7-585x878.jpg 585w" sizes="100vw" width="4000"></p><p>I LOVE this One-Pot Pasta recipe because it’s&nbsp;<strong>fuss-free</strong>, <strong>easy</strong>, and <strong>downright delicious</strong>!&nbsp; I personally think that one-pot noodles&nbsp;<i>taste better too</i>. Because they’re simmering in our actual sauce (and with our veggies) instead of just pasta water, they have at least double the flavor! I also like how the pasta releases a little starch as it cooks, which helps to make our sauce nice, thick, and creamy.</p><p><strong>If you’re looking for more one-pot pasta recipes, </strong>you’ll also love this&nbsp;<a href="https://frommybowl.com/one-pot-mushroom-stroganoff/"><strong>One Pot Vegan Mushroom Stroganoff</strong></a>, this&nbsp;<a href="https://frommybowl.com/one-pot-pumpkin-pasta/"><strong>One Pot Pumpkin Pasta</strong></a>, and this&nbsp;<a href="https://frommybowl.com/one-pot-spinach-artichoke-pasta/"><strong>One Pot Spinach &amp; Artichoke Pasta</strong></a><strong>!&nbsp;</strong></p><p><img src="https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl_Pinterest.png" style="width: 150px; height: auto; alt="This Easy One-Pot Pasta is the perfect weeknight dinner - uncooked pasta, veggies, and sauce combine and cook together in one pot in only 20 minutes! #onepot #pasta #vegan #vegandinner #plantbased | frommybowl.com" srcset="https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl_Pinterest.png 735w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl_Pinterest-200x300.png 200w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl_Pinterest-683x1024.png 683w, https://frommybowl.com/wp-content/uploads/2020/01/One_Pot_Pasta_Vegetables_Vegan_FromMyBowl_Pinterest-585x877.png 585w" sizes="100vw" width="735"></p>
            
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
