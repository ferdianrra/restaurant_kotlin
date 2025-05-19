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
        title = "Es Teh1",
        image = R.drawable.es_teh,
        price = 20000.0,
        description = "Nikmati kesegaran yang menyegarkan dengan Es Teh Segar kami, pilihan sempurna untuk menyegarkan dahaga di hari yang panas. Teh hitam yang diseduh dengan sempurna, dipadukan dengan es batu yang dingin, memberikan sensasi menyegarkan di setiap tegukan. Ditambah dengan sedikit gula untuk memberikan keseimbangan rasa manis yang pas, menjadikan minuman ini pilihan yang tidak hanya menyegarkan, tapi juga menenangkan. Nikmati es teh kami yang sederhana namun penuh rasa, cocok untuk menemani setiap momen santai Anda."
    ),
    Menu(
        title = "Burger1",
        image = R.drawable.burger,
        price = 45000.0,
        description = "Nikmati sensasi cita rasa lezat dalam setiap gigitan dengan Burger Classic kami. Terdiri dari roti panggang yang empuk, dipadukan dengan patty daging sapi berkualitas tinggi yang dimasak sempurna. Disertai dengan sayuran segar seperti selada, tomat, dan bawang, serta diberi tambahan saus mayo atau saus tomat yang gurih. Setiap lapisan membawa kelezatan yang seimbang, menjadikan burger ini pilihan sempurna untuk setiap selera. Dapatkan pengalaman makan yang memuaskan dengan burger yang tidak hanya mengenyangkan, tapi juga menyegarkan!"
    ),
    Menu(
        title = "Es Teh2",
        image = R.drawable.es_teh,
        price = 20000.0,
        description = "Nikmati kesegaran yang menyegarkan dengan Es Teh Segar kami, pilihan sempurna untuk menyegarkan dahaga di hari yang panas. Teh hitam yang diseduh dengan sempurna, dipadukan dengan es batu yang dingin, memberikan sensasi menyegarkan di setiap tegukan. Ditambah dengan sedikit gula untuk memberikan keseimbangan rasa manis yang pas, menjadikan minuman ini pilihan yang tidak hanya menyegarkan, tapi juga menenangkan. Nikmati es teh kami yang sederhana namun penuh rasa, cocok untuk menemani setiap momen santai Anda."
    ),
//    Menu(
//        title = "Steak1",
//        image = R.drawable.steak,
//        price = 185000.0,
//        description = "Rasakan kelezatan dan keempukan Steak Premium kami, yang dibuat dari potongan daging pilihan berkualitas tinggi. Setiap potongan daging dipanggang dengan sempurna, menghasilkan rasa juicy dan penuh cita rasa yang menggugah selera. Dengan permukaan luar yang sedikit kecokelatan dan bagian dalam yang tetap lembut, steak ini disajikan dengan pilihan tingkat kematangan sesuai selera, dari medium rare hingga well done. Ditambah dengan saus pilihan seperti saus lada hitam atau mushroom gravy, serta sayuran segar dan kentang panggang sebagai pendamping, setiap suapan steak ini memberikan pengalaman makan yang luar biasa. Ideal untuk Anda yang ingin menikmati hidangan daging yang kaya rasa dan memuaskan."
//    ),
    Menu(
        title = "Milk Shake1",
        image = R.drawable.milkshake,
        price = 50000.0,
        description = "Nikmati kelembutan dan kelezatan Milkshake kami yang creamy dan penuh rasa! Dibuat dengan es krim berkualitas tinggi yang dipadukan dengan susu segar, menciptakan tekstur yang lembut dan kental di setiap tegukan. Milkshake ini hadir dalam berbagai varian rasa, seperti cokelat, vanila, atau stroberi, yang siap memanjakan lidah Anda. Dilengkapi dengan whipped cream di atasnya dan taburan topping manis, setiap gelas milkshake memberikan pengalaman manis yang tak terlupakan. Cocok sebagai teman santai atau pencuci mulut yang menyenangkan!"
    ),
    Menu(
        title = "Jus Stroberi1",
        image = R.drawable.strawberry_juice,
        price = 45000.0,
        description = "Nikmati kesegaran alami dari Jus Stroberi Segar kami, dibuat dari stroberi pilihan yang dipetik langsung dari kebun terbaik. Jus ini menyajikan rasa manis dan asam yang seimbang, dengan tekstur halus dan segar di setiap tegukan. Dipadukan dengan sedikit pemanis alami untuk menambah kesegaran, jus stroberi ini adalah pilihan sempurna untuk menyegarkan tubuh Anda di hari yang panas. Dilengkapi dengan es batu untuk sensasi dingin yang menyegarkan, jus stroberi ini juga penuh dengan kandungan vitamin C yang baik untuk tubuh. Segarkan harimu dengan jus stroberi yang menyegarkan dan menyehatkan!"
    ),
    Menu(
        title = "Es Teh3",
        image = R.drawable.es_teh,
        price = 20000.0,
        description = "Nikmati kesegaran yang menyegarkan dengan Es Teh Segar kami, pilihan sempurna untuk menyegarkan dahaga di hari yang panas. Teh hitam yang diseduh dengan sempurna, dipadukan dengan es batu yang dingin, memberikan sensasi menyegarkan di setiap tegukan. Ditambah dengan sedikit gula untuk memberikan keseimbangan rasa manis yang pas, menjadikan minuman ini pilihan yang tidak hanya menyegarkan, tapi juga menenangkan. Nikmati es teh kami yang sederhana namun penuh rasa, cocok untuk menemani setiap momen santai Anda."
    ),
)