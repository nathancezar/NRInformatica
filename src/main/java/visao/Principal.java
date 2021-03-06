/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.io.File;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author nathan.cezar
 */
public class Principal extends javax.swing.JFrame {

    private final String diretorio = "home/nathancezar/NetBeansProjects/Git/NRInformatica/src/main/java/visao";
    protected File imagen = new File(diretorio);
    File[] imagens = imagen.listFiles((File pathname) -> pathname.getName().toLowerCase().endsWith(".jpeg"));
    ArrayList<JButton> botoesProdutos = new ArrayList();

    /**
     * Creates new form principal
     */
    public Principal() {
        initComponents();
        
        for (int i = 0; i < imagens.length; ++i) {
            System.out.println(imagens[i]);
        }
       
        botoesProdutos.add(jButton5);
        botoesProdutos.add(jButton6);
        botoesProdutos.add(jButton7);
        botoesProdutos.add(jButton8);
        botoesProdutos.add(jButton9);
        botoesProdutos.add(jButton10);
        botoesProdutos.add(jButton11);
        botoesProdutos.add(jButton12);

        int i = 0;
        for (JButton botao : botoesProdutos) {
            botao.setIcon((Icon) imagens[i]);
            i++;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Principal = new javax.swing.JPanel();
        Produtos = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        NomeProduto1 = new javax.swing.JLabel();
        NomeProduto2 = new javax.swing.JLabel();
        NomeProduto3 = new javax.swing.JLabel();
        NomeProduto4 = new javax.swing.JLabel();
        NomeProduto5 = new javax.swing.JLabel();
        NomeProduto6 = new javax.swing.JLabel();
        NomeProduto7 = new javax.swing.JLabel();
        NomeProduto8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        banner = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1080, 720));
        setResizable(false);

        Principal.setBackground(new java.awt.Color(122, 119, 116));
        Principal.setForeground(new java.awt.Color(139, 132, 132));
        Principal.setPreferredSize(new java.awt.Dimension(1080, 720));

        jButton5.setText("jButton5");
        jButton5.setPreferredSize(new java.awt.Dimension(200, 150));

        jButton6.setText("jButton5");
        jButton6.setPreferredSize(new java.awt.Dimension(200, 150));

        jButton7.setText("jButton5");
        jButton7.setPreferredSize(new java.awt.Dimension(200, 150));

        jButton8.setText("jButton5");
        jButton8.setPreferredSize(new java.awt.Dimension(200, 200));

        jButton9.setText("jButton5");
        jButton9.setPreferredSize(new java.awt.Dimension(200, 150));

        jButton10.setText("jButton5");
        jButton10.setPreferredSize(new java.awt.Dimension(200, 150));

        jButton11.setText("jButton5");
        jButton11.setPreferredSize(new java.awt.Dimension(200, 150));

        jButton12.setText("jButton5");
        jButton12.setPreferredSize(new java.awt.Dimension(200, 150));

        NomeProduto1.setText("Nome Produto");

        NomeProduto2.setText("Nome Produto");

        NomeProduto3.setText("Nome Produto");

        NomeProduto4.setText("Nome Produto");

        NomeProduto5.setText("Nome Produto");

        NomeProduto6.setText("Nome Produto");

        NomeProduto7.setText("Nome Produto");

        NomeProduto8.setText("Nome Produto");

        jTextField1.setText("Digite o que procura");

        jButton1.setText("RF Informática");

        jButton4.setText("Cadastro");

        jButton2.setText("Buscar");

        jButton3.setText("Carrinho");

        javax.swing.GroupLayout ProdutosLayout = new javax.swing.GroupLayout(Produtos);
        Produtos.setLayout(ProdutosLayout);
        ProdutosLayout.setHorizontalGroup(
            ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProdutosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProdutosLayout.createSequentialGroup()
                        .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ProdutosLayout.createSequentialGroup()
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ProdutosLayout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(108, 108, 108))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProdutosLayout.createSequentialGroup()
                        .addComponent(NomeProduto5)
                        .addGap(105, 105, 105)
                        .addComponent(NomeProduto6)
                        .addGap(122, 122, 122)
                        .addComponent(NomeProduto7)
                        .addGap(127, 127, 127)
                        .addComponent(NomeProduto8)
                        .addGap(154, 154, 154))))
            .addGroup(ProdutosLayout.createSequentialGroup()
                .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProdutosLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(NomeProduto1)
                        .addGap(105, 105, 105)
                        .addComponent(NomeProduto2)
                        .addGap(122, 122, 122)
                        .addComponent(NomeProduto3)
                        .addGap(127, 127, 127)
                        .addComponent(NomeProduto4))
                    .addGroup(ProdutosLayout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jButton1)
                        .addGap(12, 12, 12)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton2)
                        .addGap(66, 66, 66)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ProdutosLayout.setVerticalGroup(
            ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdutosLayout.createSequentialGroup()
                .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2))
                    .addGroup(ProdutosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jButton4))
                            .addComponent(jButton3))))
                .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProdutosLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NomeProduto1)
                    .addComponent(NomeProduto2)
                    .addComponent(NomeProduto3)
                    .addComponent(NomeProduto4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NomeProduto5)
                    .addComponent(NomeProduto6)
                    .addComponent(NomeProduto7)
                    .addComponent(NomeProduto8))
                .addGap(41, 41, 41))
        );

        banner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/images/banner.jpg"))); // NOI18N
        banner.setFocusable(false);

        javax.swing.GroupLayout PrincipalLayout = new javax.swing.GroupLayout(Principal);
        Principal.setLayout(PrincipalLayout);
        PrincipalLayout.setHorizontalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Produtos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(banner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PrincipalLayout.setVerticalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addComponent(banner)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Produtos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NomeProduto1;
    private javax.swing.JLabel NomeProduto2;
    private javax.swing.JLabel NomeProduto3;
    private javax.swing.JLabel NomeProduto4;
    private javax.swing.JLabel NomeProduto5;
    private javax.swing.JLabel NomeProduto6;
    private javax.swing.JLabel NomeProduto7;
    private javax.swing.JLabel NomeProduto8;
    private javax.swing.JPanel Principal;
    private javax.swing.JPanel Produtos;
    private javax.swing.JLabel banner;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
