import java.io.*;
 
public class Principal {
    public static void main(String[] args) {
        try {
            RandomAccessFile arq;
            Reclamacao reader = new Reclamacao();

            if (!(new File("./dados.db").exists())) {
                arq = new RandomAccessFile("./dados.db", "rw");
                arq.writeInt(0);
                arq.close();
            }
            add_reclamacao();
            // arq = new RandomAccessFile("./dados.db", "r");
            // listFile(arq);
            // arq.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void add_reclamacao(){
        try {
            RandomAccessFile arq;
            Reclamacao reader = new Reclamacao();
            arq = new RandomAccessFile("dados.db", "rw");
            int cod_now = readLastCode(arq);
						System.out.println(cod_now);
            String nome = MyIO.readLine("Entre com o nome do reclamante: ");
            String texto = MyIO.readLine("Entre com a reclamacao: ");
            String data = MyIO.readLine("Entre com a data da reclamacao: ");
            String linha = MyIO.readLine("Entre com a linha que aconteceu a o evento: ");
            Reclamacao r = new Reclamacao(0, cod_now+1, nome, texto, data, linha);
 
            arq.seek(arq.length());
            long pos = arq.getFilePointer();
            r.writeObject(arq);
 						arq.seek(0);
            // update the header
            arq.writeInt(cod_now+1);
            // updateLastCode(arq, cod_now);
 
            // arq.seek(pos);
            System.out.println("Reclamacao abaixo cadastrada com sucesso!!!");
            // reader.readObject(arq);
            // System.out.println(reader);
            arq.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
 
    protected static int readLastCode(RandomAccessFile arq) throws IOException {
        return arq.readInt();
    }
 
    protected static void updateLastCode(RandomAccessFile arq, int cod) throws IOException {
        arq.writeInt(cod+1);
    }

    protected static void listFile(RandomAccessFile arq) throws IOException {
        Reclamacao reader = new Reclamacao();
        long pos = arq.getFilePointer();
        arq.seek(pos);
        reader.readObject(arq);
        System.out.println(reader);
    }

}
 
 
 
// Saída:
 
// 0
// 39
 
// Código........: 2
// Matrícula.....: 559816
// Nome do Aluno.: João Silva
// Matéria.......: AC2
// Nota..........: 18.9
 
// -------------------------------------------
 
// Código........: 1
// Matrícula.....: 559855
// Nome do Aluno.: Jorge Oliveira
// Matéria.......: AED
// Nota..........: 20.5
