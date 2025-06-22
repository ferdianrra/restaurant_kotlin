package com.ferdi.restaurankotlin.data.model

import com.ferdi.restaurankotlin.R
import kotlinx.serialization.Serializable


@Serializable
data class Menu(
    val title: String,
    val description: String,
    val image: Int,
    val price: Double
)

val dummyMenu: List<Menu> = listOf(
    Menu(
        title = "Burger",
        image = R.drawable.burger,
        price = 45000.0,
        description = "Nikmati sensasi cita rasa lezat dalam setiap gigitan dengan Burger Classic kami. Terdiri dari roti panggang yang empuk, dipadukan dengan patty daging sapi berkualitas tinggi yang dimasak sempurna. Disertai dengan sayuran segar seperti selada, tomat, dan bawang, serta diberi tambahan saus mayo atau saus tomat yang gurih. Setiap lapisan membawa kelezatan yang seimbang, menjadikan burger ini pilihan sempurna untuk setiap selera. Dapatkan pengalaman makan yang memuaskan dengan burger yang tidak hanya mengenyangkan, tapi juga menyegarkan!"
    ),
    Menu(
        title = "Es Teh",
        image = R.drawable.es_teh,
        price = 20000.0,
        description = "Nikmati kesegaran yang menyegarkan dengan Es Teh Segar kami, pilihan sempurna untuk menyegarkan dahaga di hari yang panas. Teh hitam yang diseduh dengan sempurna, dipadukan dengan es batu yang dingin, memberikan sensasi menyegarkan di setiap tegukan. Ditambah dengan sedikit gula untuk memberikan keseimbangan rasa manis yang pas, menjadikan minuman ini pilihan yang tidak hanya menyegarkan, tapi juga menenangkan. Nikmati es teh kami yang sederhana namun penuh rasa, cocok untuk menemani setiap momen santai Anda."
    ),
    Menu(
        title = "Steak",
        image = R.drawable.steak,
        price = 185000.0,
        description = "Rasakan kelezatan dan keempukan Steak Premium kami, yang dibuat dari potongan daging pilihan berkualitas tinggi. Setiap potongan daging dipanggang dengan sempurna, menghasilkan rasa juicy dan penuh cita rasa yang menggugah selera. Dengan permukaan luar yang sedikit kecokelatan dan bagian dalam yang tetap lembut, steak ini disajikan dengan pilihan tingkat kematangan sesuai selera, dari medium rare hingga well done. Ditambah dengan saus pilihan seperti saus lada hitam atau mushroom gravy, serta sayuran segar dan kentang panggang sebagai pendamping, setiap suapan steak ini memberikan pengalaman makan yang luar biasa. Ideal untuk Anda yang ingin menikmati hidangan daging yang kaya rasa dan memuaskan."
    ),
    Menu(
        title = "Milk Shake",
        image = R.drawable.milkshake,
        price = 50000.0,
        description = "Nikmati kelembutan dan kelezatan Milkshake kami yang creamy dan penuh rasa! Dibuat dengan es krim berkualitas tinggi yang dipadukan dengan susu segar, menciptakan tekstur yang lembut dan kental di setiap tegukan. Milkshake ini hadir dalam berbagai varian rasa, seperti cokelat, vanila, atau stroberi, yang siap memanjakan lidah Anda. Dilengkapi dengan whipped cream di atasnya dan taburan topping manis, setiap gelas milkshake memberikan pengalaman manis yang tak terlupakan. Cocok sebagai teman santai atau pencuci mulut yang menyenangkan!"
    ),
    Menu(
        title = "Jus Stroberi",
        image = R.drawable.strawberry_juice,
        price = 45000.0,
        description = "Nikmati kesegaran alami dari Jus Stroberi Segar kami, dibuat dari stroberi pilihan yang dipetik langsung dari kebun terbaik. Jus ini menyajikan rasa manis dan asam yang seimbang, dengan tekstur halus dan segar di setiap tegukan. Dipadukan dengan sedikit pemanis alami untuk menambah kesegaran, jus stroberi ini adalah pilihan sempurna untuk menyegarkan tubuh Anda di hari yang panas. Dilengkapi dengan es batu untuk sensasi dingin yang menyegarkan, jus stroberi ini juga penuh dengan kandungan vitamin C yang baik untuk tubuh. Segarkan harimu dengan jus stroberi yang menyegarkan dan menyehatkan!"
    ),
    Menu(
        title = "Pizza",
        image = R.drawable.pizza,
        price = 60000.0,
        description =  "Pizza adalah hidangan khas Italia yang terdiri dari adonan roti pipih yang dipanggang dan diberi berbagai topping seperti saus tomat, keju mozzarella, daging, sayuran, dan rempah-rempah. Dengan kombinasi rasa gurih, renyah, dan lezat, pizza menjadi makanan favorit berbagai kalangan dan cocok dinikmati kapan saja."
    ),
    Menu(
        title = "Pasta",
        image = R.drawable.pasta,
        price = 50000.0,
        description = "Pasta adalah makanan khas Italia yang terbuat dari adonan tepung terigu dan air, dibentuk dalam berbagai macam bentuk seperti spaghetti, penne, fusilli, hingga lasagna. Disajikan dengan berbagai saus seperti bolognese, carbonara, atau pesto, pasta menjadi hidangan yang kaya rasa dan cocok dinikmati dalam berbagai suasana."
    ),
    Menu(
        title = "Sandwich",
        image = R.drawable.sandwich,
        price = 33000.0,
        description = "Sandwich adalah hidangan praktis yang terdiri dari dua potong roti yang diisi dengan berbagai bahan seperti daging, keju, sayuran segar, dan saus pilihan. Cocok sebagai menu sarapan, makan siang, atau camilan sehat yang mengenyangka"
    ),
    Menu(
        title = "Coffee Latte",
        image = R.drawable.coffee_latte,
        price = 28000.0,
        description = "Coffe Latte adalah minuman kopi khas Italia yang dibuat dari kombinasi espresso dan susu steam (susu yang dipanaskan dengan uap), menghasilkan rasa kopi yang lembut dan creamy. Cocok dinikmati saat pagi hari atau sebagai teman bersantai."
    ),
    Menu(
        title = "Macaroni",
        image = R.drawable.macaroni,
        price = 55000.0,
        description = "Macaroni and Cheese adalah hidangan klasik yang terdiri dari pasta makaroni yang dimasak dan diselimuti saus keju yang creamy dan gurih. Cocok sebagai comfort food yang disukai semua usia."
    ),
    Menu(
        title = "Fish and Chips",
        image = R.drawable.fish_and_chips,
        price = 35000.0,
        description = "Fish and Chips adalah hidangan khas Inggris yang terdiri dari ikan fillet berbalut tepung renyah yang digoreng keemasan, disajikan dengan kentang goreng tebal. Perpaduan tekstur garing di luar dan lembut di dalam menjadikannya sajian yang nikmat dan mengenyangkan."
    ),
    Menu(
        title = "Salad",
        image = R.drawable.salad,
        price = 45000.0,
        description = "Salad sayuran adalah hidangan sehat yang terdiri dari berbagai sayuran segar seperti selada, tomat, mentimun, wortel, dan paprika, biasanya disajikan dengan dressing seperti vinaigrette, thousand island, atau caesar. Rendah kalori dan kaya serat, cocok untuk kamu yang ingin hidup lebih sehat."
    ),
    Menu(
        title = "Hotdog",
        image = R.drawable.hotdog,
        price = 32000.0,
        description = "Hotdog adalah makanan cepat saji yang terdiri dari sosis panggang atau rebus yang disajikan di dalam roti panjang, biasanya ditambahkan saus seperti mustard, mayones, dan saus tomat, serta topping seperti bawang, acar, atau keju. Praktis, mengenyangkan, dan cocok untuk dinikmati kapan saja.\n"
    ),
    Menu(
        title = "Fried Chicken",
        image = R.drawable.fried_chicken,
        price = 38000.0,
        description = "Fried Chicken adalah ayam yang dibumbui dan digoreng hingga renyah berwarna keemasan. Dagingnya yang juicy berpadu dengan kulit yang garing menjadikannya hidangan favorit banyak orang, cocok disantap bersama nasi, kentang goreng, atau saus sambal."
    ),
    Menu(
        title = "Fried Rice",
        image = R.drawable.fried_rice,
        price = 40000.0,
        description = "Aroma wangi nasi yang ditumis dengan bumbu khas, telur, dan potongan ayam atau udang menciptakan rasa yang menggugah selera. Fried Rice adalah solusi lezat di setiap waktu makan—praktis, nikmat, dan penuh cita rasa lokal yang memanjakan lidah."
    ),
    Menu(
        title = "Ice Lemon Tea",
        image = R.drawable.lemon_tea,
        price = 26000.0,
        description = "Lemon Tea adalah minuman teh yang dipadukan dengan perasan lemon segar, menghasilkan rasa yang menyegarkan dengan perpaduan manis dan asam yang seimbang. Cocok dinikmati dingin maupun hangat, sebagai pelepas dahaga atau teman bersantai."
    ),
    Menu(
        title = "Fettuccine",
        image = R.drawable.fettuccine_alfredo,
        price = 60000.0,
        description = "Pasta fettuccine yang dimasak dengan saus krim keju yang lembut dan gurih, biasanya diperkaya dengan taburan keju parmesan dan sedikit peterseli. Hidangan creamy ini cocok untuk kamu yang mencari kenyamanan dalam setiap suapan."
    ),
    Menu(
        title = "Chicken Wings",
        image = R.drawable.chicken_wings,
        price = 27000.0,
        description = "Sayap ayam yang digoreng renyah lalu dilapisi bumbu pilihan seperti BBQ, honey garlic, atau spicy buffalo. Cocok untuk camilan atau menu utama yang bisa dinikmati bareng saus cocolan favorit.\n"
    ),
    Menu(
        title = "Mashed Potato",
        image = R.drawable.mashed_potato,
        price = 28000.0,
        description = "Kentang tumbuk yang lembut dan creamy, dipadukan dengan mentega dan sedikit susu, menciptakan rasa gurih yang kaya. Sering disajikan sebagai pelengkap menu utama seperti steak atau ayam panggang."
    ),
    Menu(
        title = "Onion Rings",
        image = R.drawable.onion_rings,
        price = 25000.0,
        description = "Irisan bawang bombay yang dibalut tepung berbumbu lalu digoreng hingga garing keemasan. Renyah di luar, manis dan lembut di dalam sehingga cocok untuk camilan atau side dish yang bikin nagih."
    ),
    Menu(
        title = "Chicken Wrap",
        image = R.drawable.chicken_wrap,
        price = 28500.0,
        description = "Daging ayam yang dimasak dengan bumbu spesial, bisa digoreng, dipanggang, atau grilled. Dibungkus dalam tortilla lembut bersama sayuran segar seperti selada, tomat, dan timun, lalu diberi saus mayo atau dressing pilihan. Praktis, lezat, dan cocok dinikmati kapan saja."
    ),
)