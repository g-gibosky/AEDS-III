import java.io.*;
 
public class Reclamacao {
         private int codigo;
         private int lapide;
         private String nome;
         private String texto;
         private String data;
         private String linha;
 
         public Reclamacao() {
                    codigo = 0;
                    lapide = 0;
                    data = "";
                    nome = "";
                    texto = "";
                    linha = "";
         }
 
         public Reclamacao(int lapide, int codigo, String nome, String texto, String data, String linha) {
                    this.codigo = codigo;
                    this.lapide = lapide;
                    this.data = data;
                    this.nome = nome;
                    this.texto = texto;
                    this.linha = linha;
         }
 
				public String toString() {
          return  "\nCódigo........: " + codigo +
                  "\nLapide........: " + lapide +
                  "\nData.....: " + data +
                  "\nNome.........: " + nome +
                  "\nTexto.......: " + texto +
                  "\nlinha..........: " + linha;
       	}
 
         protected void writeObject(RandomAccessFile arq) throws IOException {
                    ByteArrayOutputStream registro = new ByteArrayOutputStream();
                    DataOutputStream saida = new DataOutputStream(registro);
                    saida.writeInt(codigo);
                    saida.writeShort(0);
                    saida.writeUTF(data);
                    saida.writeUTF(nome);
                    saida.writeUTF(texto);
                    saida.writeUTF(linha);
                    arq.writeShort(saida.size());
                    arq.write(registro.toByteArray());
         }
 
 
         protected void readObject(RandomAccessFile arq) throws IOException {
                    int tamanho = arq.readShort();
                    byte[] array = new byte[tamanho];
 
                    if(arq.read(array) != tamanho) {
                             throw new IOException("Inconsistência nos dados");
                    }
 
                    ByteArrayInputStream registro = new ByteArrayInputStream(array);
                    DataInputStream entrada = new DataInputStream(registro);
                    codigo = entrada.readInt();
                    lapide = entrada.readShort();
                    data = entrada.readUTF();
                    nome = entrada.readUTF();
                    texto = entrada.readUTF();
                    linha = entrada.readUTF();
         }
 
        // protected int readLastCode(RandomAccessFile arq) throws IOException {
        //  int tamanho = arq.readShort();
        //  byte[] array = new byte[tamanho];
 
        //  if(arq.read(array) != tamanho) {
        //           throw new IOException("Inconsistência nos dados");
        //  }
 
        //  ByteArrayInputStream registro = new ByteArrayInputStream(array);
        //  DataInputStream entrada = new DataInputStream(registro);
 
        //  codigo = entrada.readInt();
        //  return codigo;
        // }
 
        // protected void add_reclamacao(){
             
        //  try {
        //      RandomAccessFile arq;
        //      arq = new RandomAccessFile("dados.db", "r");
        //      String nome = MyIO.readString("Entre com o nome do reclamante: ");
        //      String texto = MyIO.readString("Entre com a reclamacao: ");
        //      String data = MyIO.readString("Entre com a data da reclamacao: ");
        //      String linha = MyIO.readString("Entre com a linha que aconteceu a o evento: ");
        //      Reclamacao r = new Reclamacao(readLastCode(arq)+1, nome, texto, data, linha);
 
        //      long pos1 = arq.getFilePointer();
        //      r.writeObject(arq);
        //      arq.close();
        //  }catch(IOException e) {
        //      e.printStackTrace();
        //  }
        // }
        // protected void busca_reclamacao(){
        //  arq.seek(pos2);
        //  reclamacao1.readObject(arq);
        //  System.out.println(reclamacao1);
 
        //  arq.seek(pos1);
        //  reclamacao1.readObject(arq);
        //  System.out.println(reclamacao1);
        // }
 
}
