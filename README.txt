Project sử dụng :
Editor: IntelliJ
jdk : 18
Junit 5.8.1
sqlite-jdbc-344.0.0
javafx
Hướng dẫn chạy nếu bị lỗi dạng : 
cannot access class com.sun.javafx.util.Utils (in module javafx.graphics) because module javafx.graphics does not export com.sun.javafx.util to unnamed module 
Add VM option với nội dung như sau ( copy tất cả) : 
--add-modules
javafx.controls,javafx.fxml,javafx.media
--add-reads
javafx.graphics=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.util=ALL-UNNAMED
--add-opens
javafx.base/com.sun.javafx.reflect=ALL-UNNAMED
--add-opens
javafx.base/com.sun.javafx.beans=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED
--add-opens
javafx.base/com.sun.javafx.logging=ALL-UNNAMED
--add-opens
javafx.media/com.sun.media.jfxmedia.events=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.prism=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.glass.ui=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.geom.transform=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.tk=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.glass.utils=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.font=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.text=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.scene.text=ALL-UNNAMED
--add-opens
javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.scene.input=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.application=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.geom=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.prism.paint=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.javafx.iio=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.scenario.effect=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.scenario.effect.impl.prism=ALL-UNNAMED
--add-opens
javafx.graphics/com.sun.prism.image=ALL-UNNAMED