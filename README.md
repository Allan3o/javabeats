🎧 JavaBeats  
Projeto desenvolvido para reproduzir músicas diretamente pelo terminal usando Java.

🧰 Tecnologias utilizadas  
- Java (JDK 8 ou superior)  
- Bibliotecas nativas de áudio do Java  
- Execução via terminal (SEM interface gráfica)

🚀 Como rodar o projeto  

1️⃣ Instale o Java  
Baixe e instale o JDK (Java Development Kit):  
🔗 https://www.oracle.com/java/technologies/downloads/  
Ou, se preferir versões open-source:  
🔗 https://adoptium.net/

---

2️⃣ Verifique se o Java foi instalado corretamente  
No terminal, execute:  
java -version  
javac -version  
Se mostrar a versão instalada, está tudo certo.

---

3️⃣ Baixe o projeto  
Clone o repositório com:  
git clone https://github.com/Allan3o/javabeats.git  
Acesse o diretório:  
cd javabeats

---

4️⃣ Adicione suas músicas  
Dentro da pasta do projeto, existe a pasta:  
Musicas/  
Coloque dentro dela os arquivos de áudio que você deseja tocar.  
⚠️ Dica: use arquivos .wav ou .mp3 dependendo da biblioteca usada no código.

---

5️⃣ Compile o projeto  
javac -d bin src/**/*.java  
Isso criará os arquivos compilados dentro da pasta bin/.

---

6️⃣ Execute o player  
java -cp bin Main  
Se a classe principal estiver dentro de um pacote, use o nome completo, por exemplo:  
java -cp bin play.Main

---

7️⃣ Use o JavaBeats 🎶  
Após iniciar o programa pelo terminal:  
- o sistema lista as músicas disponíveis  
- você escolhe a faixa  
- a reprodução começa automaticamente  

Simples, direto e funcional — tudo via terminal!
