# Desafio STI3

## O desafio consiste na reaquisição de uma API de pedidos, que serão armazenados no banco local do dispositivo.
## O banco foi feito em Room, uma biblioteca SQLite que facilita muito a manipulação e persistência de dados locais.
## Para facilitar na reaquisição dos dados da API, foi usado a biblioteca Retrofit, que auxilia na hora de recuperar os dados do json e converter pra gson.


# Tela inicial de pedidos
Listagem feita com RecyclerView, para o efeito de loading foi usado a biblioteca ShimmerEffect do Facebook.



https://user-images.githubusercontent.com/95176405/210450361-7ea6a8d2-f6f9-413b-ae96-3f598d0654d9.mp4








# Aba de pesquisa
As telas foram separadas em Fragments, e incluídas em um NavigationDrawer para o efeito de abas, é exibido uma SnackBar se o cliente digitado não existir.


https://user-images.githubusercontent.com/95176405/210458236-cc84398d-e252-4666-a3ff-928d1b8cc809.mp4





# Detalhes do pedido
Tela que exibe os dados fornecidos pela API, como dados da entrega e do cliente, também possui uma animação que muda dependendo do status da compra.



https://user-images.githubusercontent.com/95176405/210458847-dd771ea6-fd68-4935-b1ca-727db01cf7d3.mp4





# Listagem de pagamentos, e itens
Além dos dados de entrega e usuário, também é possível visualizar a listagem de pagamentos itens e valores, sendo exibido o valor de cada parcela, a quantidade de itens e outros dados.


https://user-images.githubusercontent.com/95176405/210459418-4cd5e3c9-7bd7-4ace-88a3-b7ad3e049543.mp4





# Atividade offline
O aplicativo salva tudo em seu banco de dados a cada reaquisição feita automaticamente, podendo ser exibido os dados mesmo se o dispositivo não tiver acesso a internet, porém se o aplicativo nunca tiver realizado uma reaquisição e você executá-lo, ele irá te exibir uma tela para se conectar á internet, dando a opção de ir diretamente nas configurações do wifi.


https://user-images.githubusercontent.com/95176405/210460333-611c1846-74ad-4325-81d9-adda269be015.mp4



# Ferramentas utilizadas
**IDE:** Android Studio
**Linguagens:** XML, Java, SQL
**Dependências:** Room Database, ShimmerEffect, Retrofit, RetrofitGsonConverter, LottieFiles.

#Conhecimento adquirido

Essencial o conhecimento adquirido com a manipulação da API e com os objetos em json, foi necessário manipular objetos dentro de outros objetos e listas, que deram a oportunidade de trabalhar de uma maneira diferente com esse projeto, o Retrofit ajudou muito com esse processo.
O room database também foi uma das bibliotecas que facilitaram muito o desenvolvimento do projeto, foi o meu primeiro contato com a biblioteca e pude ver a capacidade gigantesca de trabalhar usando dados locais com essa ferramenta.
A verificação de conexão com o ConnectivityManager também foi um aprendizado que irei aproveitar em futuros projetos durante minha carreira, dando a possibilidade de verificação da conexão constante.

