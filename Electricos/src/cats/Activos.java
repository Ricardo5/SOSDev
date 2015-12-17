//Paquete
package cats;

//Importaciones
import java.awt.Cursor;
import static ptovta.Princip.bIdle;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import ptovta.Login;
import ptovta.Star;




/*Catálogo de tallas y colores*/
public class Activos extends javax.swing.JFrame 
{
    /*Declara variables originales*/
    private String          sMarcaOri;
    private String          sDescripMarcOri;
    private String          sModeOri;
    private String          sDescripModOri;
    
    /*Declara contadors globales*/
    private int             iContCellEd;
    private int             iContFi;
    
    /*Para controlar si seleccionar todo o deseleccionarlo de la tabla*/
    private boolean          bSel;

    /*Variable que contiene el borde actual*/
    private javax.swing.border.Border bBordOri;
    
    
    
    /*Constructor sin argumentos*/
    public Activos() 
    {
        /*Inicaliza los componentes gráficos*/
        initComponents();
        
        /*Establece el botón por default*/
        this.getRootPane().setDefaultButton(jBNew);
        
        /*Para que las tabla tengan scroll horisontal*/
        jTab.setAutoResizeMode(0);
        
        /*Para que no se muevan las columnas*/
        jTab.getTableHeader().setReorderingAllowed(false);
        
        /*Esconde el link de ayuda*/
        jLAyu.setVisible(false);
        
        /*Centra la ventana*/
        this.setLocationRelativeTo(null);
        
        /*Establece el titulo de la ventana con El usuario, la fecha y hora*/                
        this.setTitle("Marcas y modelos, Usuario: <" + Login.sUsrG + "> " + Login.sFLog);        

        /*Inicialmente esta deseleccionada la tabla*/
        bSel        = false;
        
        //Establece el ícono de la forma
        Star.vSetIconFram(this);
        
        /*Para que la tabla este ordenada al mostrarce y al dar clic en el nombre de la columna*/
        TableRowSorter trs = new TableRowSorter<>((DefaultTableModel)jTab.getModel());
        jTab.setRowSorter(trs);
        trs.setSortsOnUpdates(true);
        
        /*Establece el tamaño de las columnas de la tabla*/
        jTab.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTab.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTab.getColumnModel().getColumn(2).setPreferredWidth(400);
        jTab.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTab.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        /*Activa en la tabla que se usen normamente las teclas de tabulador*/
        jTab.setFocusTraversalKeys(java.awt.KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
        jTab.setFocusTraversalKeys(java.awt.KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
        
        /*Inicializa el contador de filas en uno*/
        iContFi      = 1;
        
        /*Incializa el contador del cell editor*/
        iContCellEd = 1;                
        
        /*Crea el listener para la tabla, para cuando se modifique una celda*/
        PropertyChangeListener pr = new PropertyChangeListener() 
        {
            @Override
            public void propertyChange(PropertyChangeEvent event) 
            {
                /*Si no hay selección entonces que regresa*/
                if(jTab.getSelectedRow()==-1)
                    return;
                
                /*Obtén la propiedad que a sucedio en el control*/
                String pro = event.getPropertyName();                                
                                
                /*Si el evento fue por entrar en una celda del tabla*/
                if("tableCellEditor".equals(pro)) 
                {
                    /*Si el contador de cell editor está en 1 entonces que lea el valor original que estaba ya*/
                    if(iContCellEd==1)
                    {                        
                        /*Obtén algunos datos originales de la fila*/                        
                        sMarcaOri            = jTab.getValueAt(jTab.getSelectedRow(), 1).toString();                                                                                  
                        sDescripMarcOri      = jTab.getValueAt(jTab.getSelectedRow(), 2).toString();                                                                                                          
                        sModeOri             = jTab.getValueAt(jTab.getSelectedRow(), 3).toString();                                                                                                          
                        sDescripModOri       = jTab.getValueAt(jTab.getSelectedRow(), 4).toString();  
                        
                        /*Aumenta en uno el contador de celleditor*/
                        ++iContCellEd;
                    }
                    /*Else, el contador de cell editor es 2, osea que va de salida*/
                    else
                    {
                        /*Reinicia el conteo*/
                        iContCellEd         = 1;
                            
                        /*Coloca los valores origianales*/
                        jTab.setValueAt(sMarcaOri,          jTab.getSelectedRow(), 1);
                        jTab.setValueAt(sDescripMarcOri,    jTab.getSelectedRow(), 2);
                        jTab.setValueAt(sModeOri,           jTab.getSelectedRow(), 3);
                        jTab.setValueAt(sDescripModOri,     jTab.getSelectedRow(), 4);
                    }
                                        
                }/*Fin de if("tableCellEditor".equals(property)) */
                
            }/*Fin de public void propertyChange(PropertyChangeEvent event) */            
        };
        
        /*Establece el listener para la tabla de marcs*/
        jTab.addPropertyChangeListener(pr);
                                
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
       //Carga todas las zonas 
        vCargarZonas(con);
        
        //Carga todas las clasificaciones
        vCargarClasificaciones(con);
        
        //Carga a los responsables
        vCargarResponsables(con);
        
        //Cierra la base de datos
        if(Star.iCierrBas(con)==-1)
            return;
            
        /*Obtén todas las tallas y colores de la base de datos*/
        vCarg();
            
    }/*Fin de public Marcs() */

    
   
    private void vCarg()
    {    
        /*Reinicia el contador de filas*/
        iContFi = 1;            
            
        /*Borra todos los item en la tabla*/
        DefaultTableModel dm = (DefaultTableModel)jTab.getModel();
        dm.setRowCount(0);
                                    
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        //Declara variables de la base de datos    
        Statement   st;
        ResultSet   rs;        
        String      sQ;
        
        /*Trae todas las marcas de la base de datos y cargalas en la tabla*/
        try
        {
            sQ = "SELECT referencia, descrip,zona, sucursal,clasificacion,responsable FROM activos";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces*/
            while(rs.next())
            {
                /*Agregalo a la tabla*/
                Object nu[]         = {iContFi, rs.getString("referencia"), rs.getString("descrip"), rs.getString("zona"), rs.getString("sucursal"), rs.getString("clasificacion"), rs.getString("responsable")};
                dm.addRow(nu);
                
                /*Aumenta en uno el contador de filas en uno*/
                ++iContFi;       
            }                        
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }            
        
        //Cierra la base de datos
        Star.iCierrBas(con);
        
    }/*Fin de private void vCarg()*/
            
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP1 = new javax.swing.JPanel();
        jBSal = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTab = new javax.swing.JTable();
        jLAyu = new javax.swing.JLabel();
        jBTod = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jCSucursal = new javax.swing.JComboBox();
        jCZona = new javax.swing.JComboBox();
        jTZona = new javax.swing.JTextField();
        jTSucursal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jBNew = new javax.swing.JButton();
        jBDel = new javax.swing.JButton();
        jBBusc = new javax.swing.JButton();
        jBMosT = new javax.swing.JButton();
        jTBusc = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jCResponsable = new javax.swing.JComboBox();
        jCClasificacion = new javax.swing.JComboBox();
        jTClasificacion = new javax.swing.JTextField();
        jTResponsable = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTDescripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTReferencia = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jP1.setBackground(new java.awt.Color(255, 255, 255));
        jP1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jP1KeyPressed(evt);
            }
        });
        jP1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBSal.setBackground(new java.awt.Color(255, 255, 255));
        jBSal.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jBSal.setForeground(new java.awt.Color(0, 102, 0));
        jBSal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/sal.png"))); // NOI18N
        jBSal.setText("Salir");
        jBSal.setToolTipText("Salir (ESC)");
        jBSal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBSal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBSalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBSalMouseExited(evt);
            }
        });
        jBSal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalActionPerformed(evt);
            }
        });
        jBSal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBSalKeyPressed(evt);
            }
        });
        jP1.add(jBSal, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 430, 90, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Activos");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jP1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 190, -1));
        jLabel2.getAccessibleContext().setAccessibleName("Activos");

        jTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "#Referencia", "Descripción", "Zona", "Sucursal", "Clasificación", "Responsable"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTab.setGridColor(new java.awt.Color(255, 255, 255));
        jTab.setNextFocusableComponent(jBBusc);
        jTab.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTab);

        jP1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 780, 290));

        jLAyu.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLAyu.setForeground(new java.awt.Color(0, 51, 204));
        jLAyu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLAyu.setText("http://Ayuda en Lìnea");
        jLAyu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLAyuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLAyuMouseExited(evt);
            }
        });
        jP1.add(jLAyu, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 430, 120, 20));

        jBTod.setBackground(new java.awt.Color(255, 255, 255));
        jBTod.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jBTod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/marct.png"))); // NOI18N
        jBTod.setText("Marcar todo");
        jBTod.setToolTipText("Marcar Todos los Registros de la Tabla (Alt+T)");
        jBTod.setNextFocusableComponent(jTab);
        jBTod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBTodMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBTodMouseExited(evt);
            }
        });
        jBTod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTodActionPerformed(evt);
            }
        });
        jBTod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBTodKeyPressed(evt);
            }
        });
        jP1.add(jBTod, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 122, 130, 18));

        jLabel14.setText("Sucursal:");
        jP1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 60, -1));

        jCSucursal.setNextFocusableComponent(jBNew);
        jCSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCSucursalActionPerformed(evt);
            }
        });
        jCSucursal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCSucursalKeyPressed(evt);
            }
        });
        jP1.add(jCSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 90, 20));

        jCZona.setNextFocusableComponent(jCSucursal);
        jCZona.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCZonaFocusLost(evt);
            }
        });
        jCZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCZonaActionPerformed(evt);
            }
        });
        jCZona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCZonaKeyPressed(evt);
            }
        });
        jP1.add(jCZona, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 90, 20));

        jTZona.setEditable(false);
        jTZona.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jTZona.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTZonaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTZonaFocusLost(evt);
            }
        });
        jTZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTZonaActionPerformed(evt);
            }
        });
        jTZona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTZonaKeyPressed(evt);
            }
        });
        jP1.add(jTZona, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 160, 20));

        jTSucursal.setEditable(false);
        jTSucursal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jTSucursal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTSucursalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTSucursalFocusLost(evt);
            }
        });
        jTSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTSucursalActionPerformed(evt);
            }
        });
        jTSucursal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTSucursalKeyPressed(evt);
            }
        });
        jP1.add(jTSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 160, 20));

        jLabel17.setText("Zona:");
        jP1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 60, -1));

        jBNew.setBackground(new java.awt.Color(255, 255, 255));
        jBNew.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jBNew.setForeground(new java.awt.Color(0, 102, 0));
        jBNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/agre.png"))); // NOI18N
        jBNew.setText("Nuevo");
        jBNew.setToolTipText("Nuevo Almacén (Ctrl+N)");
        jBNew.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBNew.setNextFocusableComponent(jBDel);
        jBNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBNewMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBNewMouseExited(evt);
            }
        });
        jBNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNewActionPerformed(evt);
            }
        });
        jBNew.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBNewKeyPressed(evt);
            }
        });
        jP1.add(jBNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 90, 20));

        jBDel.setBackground(new java.awt.Color(255, 255, 255));
        jBDel.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jBDel.setForeground(new java.awt.Color(0, 102, 0));
        jBDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/del.png"))); // NOI18N
        jBDel.setText("Borrar");
        jBDel.setToolTipText("Borrar Almacen(es) (Ctrl+SUPR)");
        jBDel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBDel.setNextFocusableComponent(jTab);
        jBDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBDelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBDelMouseExited(evt);
            }
        });
        jBDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDelActionPerformed(evt);
            }
        });
        jBDel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBDelKeyPressed(evt);
            }
        });
        jP1.add(jBDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 90, 20));

        jBBusc.setBackground(new java.awt.Color(255, 255, 255));
        jBBusc.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jBBusc.setForeground(new java.awt.Color(0, 102, 0));
        jBBusc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/busc5.png"))); // NOI18N
        jBBusc.setText("Buscar F3");
        jBBusc.setNextFocusableComponent(jTBusc);
        jBBusc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBBuscMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBBuscMouseExited(evt);
            }
        });
        jBBusc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscActionPerformed(evt);
            }
        });
        jBBusc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBBuscKeyPressed(evt);
            }
        });
        jP1.add(jBBusc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 140, 20));

        jBMosT.setBackground(new java.awt.Color(255, 255, 255));
        jBMosT.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jBMosT.setForeground(new java.awt.Color(0, 102, 0));
        jBMosT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/mostt.png"))); // NOI18N
        jBMosT.setText("Mostrar F4");
        jBMosT.setToolTipText("Mostrar Nuevamente todos los Registros");
        jBMosT.setNextFocusableComponent(jBSal);
        jBMosT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBMosTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBMosTMouseExited(evt);
            }
        });
        jBMosT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMosTActionPerformed(evt);
            }
        });
        jBMosT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBMosTKeyPressed(evt);
            }
        });
        jP1.add(jBMosT, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 430, 140, 20));

        jTBusc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jTBusc.setNextFocusableComponent(jBMosT);
        jTBusc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTBuscFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTBuscFocusLost(evt);
            }
        });
        jTBusc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTBuscKeyPressed(evt);
            }
        });
        jP1.add(jTBusc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 250, 20));

        jLabel15.setText("Responsable:");
        jP1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 70, -1));

        jCResponsable.setNextFocusableComponent(jBNew);
        jCResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCResponsableActionPerformed(evt);
            }
        });
        jCResponsable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCResponsableKeyPressed(evt);
            }
        });
        jP1.add(jCResponsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 90, 20));

        jCClasificacion.setNextFocusableComponent(jCSucursal);
        jCClasificacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCClasificacionFocusLost(evt);
            }
        });
        jCClasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCClasificacionActionPerformed(evt);
            }
        });
        jCClasificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCClasificacionKeyPressed(evt);
            }
        });
        jP1.add(jCClasificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 90, 20));

        jTClasificacion.setEditable(false);
        jTClasificacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jTClasificacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTClasificacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTClasificacionFocusLost(evt);
            }
        });
        jTClasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTClasificacionActionPerformed(evt);
            }
        });
        jTClasificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTClasificacionKeyPressed(evt);
            }
        });
        jP1.add(jTClasificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 160, 20));

        jTResponsable.setEditable(false);
        jTResponsable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jTResponsable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTResponsableFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTResponsableFocusLost(evt);
            }
        });
        jTResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTResponsableActionPerformed(evt);
            }
        });
        jTResponsable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTResponsableKeyPressed(evt);
            }
        });
        jP1.add(jTResponsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 160, 20));

        jLabel18.setText("Clasificación:");
        jP1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 70, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("*#Referencia:");
        jP1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 140, -1));
        jP1.add(jTDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 350, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("*Descripción:");
        jP1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 140, -1));
        jP1.add(jTReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 170, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jP1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

          
    /*Cuando se presiona una tecla en el formulario*/
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
        //Llama a la función escalable
        vKeyPreEsc(evt);
        
    }//GEN-LAST:event_formKeyPressed
  
        
    /*Cuando se presiona una tecla en el panel*/
    private void jP1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jP1KeyPressed
        
        //Llama a la función escalable
        vKeyPreEsc(evt);
        
    }//GEN-LAST:event_jP1KeyPressed

    
    /*Cuando se presiona el botón de salir*/
    private void jBSalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalActionPerformed
        
        /*Llama al recolector de basura y cierra la forma*/
        System.gc();                    
        this.dispose();                    
        
    }//GEN-LAST:event_jBSalActionPerformed

    
    /*Cuando se presiona una tecla en el botón salir*/
    private void jBSalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBSalKeyPressed
        
        //Llama a la función escalable
        vKeyPreEsc(evt);
        
    }//GEN-LAST:event_jBSalKeyPressed

                   
    /*Cuando se presiona una  tecla en la tabla de marcs*/
    private void jTabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabKeyPressed
        
        //Llama a la función escalable
        vKeyPreEsc(evt);
        
    }//GEN-LAST:event_jTabKeyPressed
    
            
    /*Cuando se mueve la rueda del ratón en la forma*/
    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
        
        /*Pon la bandera para saber que ya hubó un evento y no se desloguie*/
        bIdle   = true;
        
    }//GEN-LAST:event_formMouseWheelMoved

    
    /*Cuando se arrastra el mouse en la forma*/
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        
        /*Pon la bandera para saber que ya hubó un evento y no se desloguie*/
        bIdle   = true;
        
    }//GEN-LAST:event_formMouseDragged

    
    /*Cuando se mueve el ratón en la forma*/
    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        
        /*Pon la bandera para saber que ya hubó un evento y no se desloguie*/
        bIdle   = true;
        
    }//GEN-LAST:event_formMouseMoved
        
    
          
    /*Cuando el mouse entra en el campo del link de ayuda*/
    private void jLAyuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLAyuMouseEntered
        
        /*Cambia el cursor del ratón*/
        this.setCursor( new Cursor(Cursor.HAND_CURSOR));
        
    }//GEN-LAST:event_jLAyuMouseEntered

    
    /*Cuando el mouse sale del campo del link de ayuda*/
    private void jLAyuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLAyuMouseExited
        
        /*Cambia el cursor del ratón al que tenía*/
        this.setCursor( new Cursor(Cursor.DEFAULT_CURSOR));	
        
    }//GEN-LAST:event_jLAyuMouseExited

    
    /*Cuando se presiona el botón de seleccionar todo*/
    private void jBTodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTodActionPerformed

        /*Si la tabla no contiene elementos entonces regresa*/
        if(jTab.getRowCount()==0)
            return;
        
        /*Si están seleccionados los elementos en la tabla entonces*/
        if(bSel)
        {
            /*Coloca la bandera y deseleccionalos*/
            bSel    = false;
            jTab.clearSelection();
        }
        /*Else deseleccionalos y coloca la bandera*/
        else
        {
            bSel    = true;
            jTab.setRowSelectionInterval(0, jTab.getModel().getRowCount()-1);
        }

    }//GEN-LAST:event_jBTodActionPerformed

    
    /*Cuando se presiona una tecla en el botón de seleccionar todo*/
    private void jBTodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBTodKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jBTodKeyPressed

    
    /*Cuando se esta saliendo de la forma*/
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
        /*Presiona el botón de salir*/
        jBSal.doClick();
        
    }//GEN-LAST:event_formWindowClosing

        
    /*Cuando el mouse entra en el botón específico*/
    private void jBTodMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTodMouseEntered
        
        /*Cambia el color del fondo del botón*/
        jBTod.setBackground(Star.colBot);
        
    }//GEN-LAST:event_jBTodMouseEntered

                
    /*Cuando el mouse entra en el botón específico*/
    private void jBSalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBSalMouseEntered
        
        /*Cambia el color del fondo del botón*/
        jBSal.setBackground(Star.colBot);
        
    }//GEN-LAST:event_jBSalMouseEntered

        
    /*Cuando el mouse sale del botón específico*/
    private void jBTodMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTodMouseExited
        
        /*Cambia el color del fondo del botón al original*/
        jBTod.setBackground(Star.colOri);
        
    }//GEN-LAST:event_jBTodMouseExited

                
    /*Cuando el mouse sale del botón específico*/
    private void jBSalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBSalMouseExited
        
        /*Cambia el color del fondo del botón al original*/
        jBSal.setBackground(Star.colOri);
        
    }//GEN-LAST:event_jBSalMouseExited

    
    /*Cuando sucede una acción en el combo de los modelos*/
    private void jCSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCSucursalActionPerformed

        /*Si no hay datos entonces regresa*/
        if(jCSucursal.getSelectedItem()==null)
            return;
        
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;

        /*Obtiene la descripción de la talla en base a su código*/
        try
        {
            sQ = "SELECT descrip FROM sucursal WHERE cod = '" + jCSucursal.getSelectedItem().toString() + "'";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces coloca el valor en el campo*/
            if(rs.next())
                jTSucursal.setText(rs.getString("descrip"));
            else
                jTSucursal.setText("");
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }

        /*Coloca al principio del control el caret*/
        jTSucursal.setCaretPosition(0);

        //Cierra la base de datos
        Star.iCierrBas(con);
        
    }//GEN-LAST:event_jCSucursalActionPerformed

    
    /*Cuando se presiona una tecla en el combo de los colores*/
    private void jCSucursalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCSucursalKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jCSucursalKeyPressed

    
    /*Cuando sucede una acción en el combobox de marcas*/
    private void jCZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCZonaActionPerformed

        /*Si no hay datos entonces regresa*/
        if(jCZona.getSelectedItem()==null)
            return;
        
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;

        /*Obtiene la descripción de la talla en base a su código*/
        try
        {
            sQ = "SELECT descrip FROM zona WHERE cod = '" + jCZona.getSelectedItem().toString().trim() + "'";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces coloca el valor en el campo*/
            if(rs.next())
                jTZona.setText(rs.getString("descrip"));
            else
                jTZona.setText("");
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }

        /*Coloca al principio del control el caret*/
        jTZona.setCaretPosition(0);

        //Cierra la base de datos
        Star.iCierrBas(con);
       
        vCargaSucursales();
    }//GEN-LAST:event_jCZonaActionPerformed

    
    /*Cuando se presiona una tecla en el combo de tallas*/
    private void jCZonaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCZonaKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jCZonaKeyPressed

    
    /*Cuando se gana el foco del teclado en el campo de la descripción de la Marca*/
    private void jTZonaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTZonaFocusGained

        /*Selecciona todo el texto cuando gana el foco*/
        jTZona.setSelectionStart(0);jTZona.setSelectionEnd(jTZona.getText().length());

    }//GEN-LAST:event_jTZonaFocusGained

    
    /*Cuando se pierde el foco del teclado en el campo de la descripción de la marca*/
    private void jTZonaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTZonaFocusLost

        /*Coloca el caret en la posiciòn 0*/
        jTZona.setCaretPosition(0);

    }//GEN-LAST:event_jTZonaFocusLost

    
    /*Cuando se presiona una tecla en el campo de la descripción de la talla*/
    private void jTZonaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTZonaKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jTZonaKeyPressed

    
    /*Cuando se gana el foco del teclado en el campo de la descripción del color*/
    private void jTSucursalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTSucursalFocusGained

        /*Selecciona todo el texto cuando gana el foco*/
        jTSucursal.setSelectionStart(0);jTSucursal.setSelectionEnd(jTSucursal.getText().length());

    }//GEN-LAST:event_jTSucursalFocusGained

    
    /*Cuando se pierde el foco del teclado en el campo de la descripción del color*/
    private void jTSucursalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTSucursalFocusLost

        /*Coloca el caret en la posiciòn 0*/
        jTSucursal.setCaretPosition(0);

    }//GEN-LAST:event_jTSucursalFocusLost

    
    /*Cuando se presiona una tecla en el campo de la descripción del color*/
    private void jTSucursalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTSucursalKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jTSucursalKeyPressed

    
    /*Cuando el mouse entra en el botón de nuevo*/
    private void jBNewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBNewMouseEntered

        /*Cambia el color del fondo del botón*/
        jBNew.setBackground(Star.colBot);

    }//GEN-LAST:event_jBNewMouseEntered

    
    /*Cuando el mosue sale del botón de nuevo*/
    private void jBNewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBNewMouseExited

        /*Cambia el color del fondo del botón al original*/
        jBNew.setBackground(Star.colOri);

    }//GEN-LAST:event_jBNewMouseExited

    
    /*Cuando se presiona el botón de nueva partida*/
    private void jBNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNewActionPerformed

        
        /*Si por lo menos no se selecciono una talla o color entonces*/
        if(jTZona.getText().compareTo("")==0 || jTSucursal.getText().compareTo("")==0)
        {
            /*Coloca el borde rojo*/
            jCZona.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED));
            
            /*Mensajea*/
            JOptionPane.showMessageDialog(null, "Selecciona tanto marca como modelo.", "Marcas y modelos", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconAd)));

            /*Pon el foco del teclado en el campo de edición y regresa*/
            jCZona.grabFocus();
            return;
        }
        
        /*Si por lo menos no se selecciono una talla o color entonces*/
        if(jTClasificacion.getText().compareTo("")==0 )
        {
            /*Coloca el borde rojo*/
            jTClasificacion.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED));
            
            /*Mensajea*/
            JOptionPane.showMessageDialog(null, "Selecciona tanto marca como modelo.", "Marcas y modelos", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconAd)));

            /*Pon el foco del teclado en el campo de edición y regresa*/
            jTClasificacion.grabFocus();
            return;
        }
        
         /*Si por lo menos no se selecciono una talla o color entonces*/
        if(jTResponsable.getText().compareTo("")==0 )
        {
            /*Coloca el borde rojo*/
            jTResponsable.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED));
            
            /*Mensajea*/
            JOptionPane.showMessageDialog(null, "Selecciona tanto marca como modelo.", "Marcas y modelos", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconAd)));

            /*Pon el foco del teclado en el campo de edición y regresa*/
            jTResponsable.grabFocus();
            return;
        }
          /*Si por lo menos no se selecciono una talla o color entonces*/
        if(jTDescripcion.getText().compareTo("")==0 )
        {
            /*Coloca el borde rojo*/
            jTDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED));
            
            /*Mensajea*/
            JOptionPane.showMessageDialog(null, "Selecciona tanto marca como modelo.", "Marcas y modelos", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconAd)));

            /*Pon el foco del teclado en el campo de edición y regresa*/
            jTDescripcion.grabFocus();
            return;
        }
         if(jTReferencia.getText().compareTo("")==0 )
        {
            /*Coloca el borde rojo*/
           jTReferencia.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED));
            
            /*Mensajea*/
            JOptionPane.showMessageDialog(null, "Selecciona tanto marca como modelo.", "Marcas y modelos", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconAd)));

            /*Pon el foco del teclado en el campo de edición y regresa*/
            jTReferencia.grabFocus();
            return;
        }
        
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        /*Declara variables del base de datos*/
        Statement   st;
        ResultSet   rs;
        String      sQ;        

        /*Checa si ese producto con ese almacén, talla y color ya existe en la base de datos*/
        try
        {
            sQ = "SELECT referencia FROM activos WHERE referencia = '" + jTReferencia.getText().trim() + "'";                     
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces*/
            if(rs.next())
            {
                //Cierra la base de datos
                if(Star.iCierrBas(con)==-1)
                    return;

                /*Coloca el borde rojo*/
                jCSucursal.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED));

                /*Avisa al usuario*/
                JOptionPane.showMessageDialog(null, "Esta referencia ya existe", "Referencia existente", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconAd)));

                /*Pon el foco del teclado en el campo del código del almacén y regresa*/
                jCSucursal.grabFocus();
                return;
            }
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }
        
        /*Pregunta al usuario si están bien los datos*/
        Object[] op = {"Si","No"};
        int iRes    = JOptionPane.showOptionDialog(this, "¿Seguro que quieres agregar este activo", "Activos", JOptionPane.YES_NO_OPTION,  JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconDu)), op, op[0]);
        if(iRes==JOptionPane.NO_OPTION || iRes==JOptionPane.CLOSED_OPTION)
        {
            //Cierra la base de datos y regresa
            Star.iCierrBas(con);
            return;                       
        }       

        /*Guarda los datos en la base de datos*/
        try
        {
            sQ = "INSERT INTO activos(      referencia,                                         descrip,                                            zona,                                                             sucursal,                                                            sucu,                 nocaj,                 estac,                          clasificacion, responsable) " +
                                 "VALUES('"+jTReferencia.getText().replace("'", "''") +"','"+ jTDescripcion.getText().replace("'", "''") +"','" +  jCZona.getSelectedItem().toString().replace("'", "''") + "','" +jCSucursal.getSelectedItem().toString().replace("'", "''") + "', '" + Star.sSucu + "', '" + Star.sNoCaj + "', '" + Login.sUsrG + "','"+ jCClasificacion.getSelectedItem().toString().replace("'", "''") +"','" + jCResponsable.getSelectedItem().toString().replace("'", "''") +"')";
            st = con.createStatement();
            st.executeUpdate(sQ);
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }

        //Cierra la base de datos
        if(Star.iCierrBas(con)==-1)
            return;
       
        /*Agrega el registro en la tabla*/
        DefaultTableModel te    = (DefaultTableModel)jTab.getModel();
        Object nu[]             = {iContFi,jTReferencia.getText().replace("'", "''"),jTDescripcion.getText().replace("'", "''") ,jCZona.getSelectedItem().toString(), jCSucursal.getSelectedItem().toString(),jCClasificacion.getSelectedItem().toString(),jCResponsable.getSelectedItem().toString()};
        te.addRow(nu);

        /*Aumenta en uno el contador de las filas*/
        ++iContFi;

        /*Pon el foco del teclado en el campo del código del producto*/
        jCZona.grabFocus();

        /*Mensajea de éxito*/
        JOptionPane.showMessageDialog(null, "Relación agregado con éxito para marca y modelo.", "Exito al agregar", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconAd)));

    }//GEN-LAST:event_jBNewActionPerformed

    
    /*Cuando se presiona una tecla en el botón de nueva partida*/
    private void jBNewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBNewKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jBNewKeyPressed

    
    /*Cuando el mouse entra en el botón de borrar partida*/
    private void jBDelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBDelMouseEntered

        /*Cambia el color del fondo del botón*/
        jBDel.setBackground(Star.colBot);

    }//GEN-LAST:event_jBDelMouseEntered

    
    /*Cuando el mouse sale del botón de borrar partida*/
    private void jBDelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBDelMouseExited

        /*Cambia el color del fondo del botón al original*/
        jBDel.setBackground(Star.colOri);

    }//GEN-LAST:event_jBDelMouseExited

    
    /*Cuando se presiona el botón de borrar partida*/
    private void jBDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDelActionPerformed

        /*Si no hay selección en la tabla no puede seguir*/
        if(jTab.getSelectedRow()==-1)
        {
            /*Mensajea*/
            JOptionPane.showMessageDialog(null, "No has seleccionado una registro de la lista para borrar.", "Borrar Talla y Color", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconAd)));

            /*Coloca el foco del teclado en la tabla y regresa*/
            jTab.grabFocus();
            return;
        }

        /*Preguntar al usuario si esta seguro de querer borrar*/
        Object[] op = {"Si","No"};
        int iRes    = JOptionPane.showOptionDialog(this, "¿Seguro que quiere borrar el(los) registro(s)?", "Borrar Talla y Color", JOptionPane.YES_NO_OPTION,  JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconDu)), op, op[0]);
        if(iRes==JOptionPane.NO_OPTION || iRes==JOptionPane.CLOSED_OPTION)
            return;
        
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        /*Bandera para saber si hubo movimientos*/
        boolean bMov    = false;

        /*Recorre toda la selección del usuario*/
        int iSel[] = jTab.getSelectedRows();
        DefaultTableModel tm  = (DefaultTableModel)jTab.getModel();
        for(int x = iSel.length - 1; x >= 0; x--)
        {
            /*Declara variables del base de datos*/
            Statement   st;  
            String      sQ;
            ResultSet   rs;
            
            /*Si ya fue utilizado no se puede borrar
            try
            {

                sQ = "SELECT * FROM terProdCompa WHERE marc= '" + jTab.getValueAt(iSel[x], 1).toString().replace("'", "''") + "' AND model='"  + jTab.getValueAt(iSel[x], 3).toString().replace("'", "''") + "'"; 
                st = con.createStatement();
                rs = st.executeQuery(sQ);
                
                if(rs.next())
                {
                    JOptionPane.showMessageDialog(null, "Esta relación ya esta asociada a un(os) producto(s)", "Marcas y modelos", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconAd)));
                    continue;
                }
            }
            catch(SQLException expnSQL)
            {
                //Procesa el error y regresa
                Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
                return;                        
            } */         

            /*Borra el registro*/
            try
            {
                sQ = "DELETE FROM activos WHERE referencia = '" + jTab.getValueAt(iSel[x], 1).toString().replace("'", "''") + "'";                     
                st = con.createStatement();
                st.executeUpdate(sQ);
            }
            catch(SQLException expnSQL)
            {
                //Procesa el error y regresa
                Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
                return;                        
            }

            /*Coloca la bandera para saber que si hubo movimientos*/
            bMov    = true;

            /*Borralo de la tabla*/
            tm.removeRow(iSel[x]);

            /*Resta en uno el contador de filas el contador de filas en uno*/
            --iContFi;

        }/*Fin de for(int x = iSel.length - 1; x >= 0; x++)*/

        //Cierra la base de datos
        if(Star.iCierrBas(con)==-1)
            return;

        /*Mensajea de éxito si hubo miviminetos*/
        if(bMov)
            JOptionPane.showMessageDialog(null, "Registro(s) borrado(s) con éxito.", "Marcas y modelos", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconAd)));

    }//GEN-LAST:event_jBDelActionPerformed

    
    /*Cuando se presiona una tecla en el botón de borrar partida*/
    private void jBDelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBDelKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jBDelKeyPressed

    
    /*Cuando se pierde el foco del teclado en el combo de las marcas*/
    private void jCZonaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCZonaFocusLost
        
        /*Coloca el borde negro si tiene datos*/
        if(jCZona.getSelectedItem().toString().compareTo("")!=0)
            jCZona.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,255)));
        
    }//GEN-LAST:event_jCZonaFocusLost

    
    /*Cuando el mouse entra en el botón de búscar*/
    private void jBBuscMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBBuscMouseEntered

        /*Cambia el color del fondo del botón*/
        jBBusc.setBackground(Star.colBot);

        /*Guardar el borde original que tiene el botón*/
        bBordOri    = jBBusc.getBorder();

        /*Aumenta el grosor del botón*/
        jBBusc.setBorder(new javax.swing.border.LineBorder(java.awt.Color.GREEN, 3));

    }//GEN-LAST:event_jBBuscMouseEntered

    
    /*Cuando el mouse sale del botón de búscar*/
    private void jBBuscMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBBuscMouseExited

        /*Cambia el color del fondo del botón al original*/
        jBBusc.setBackground(Star.colOri);

        /*Coloca el borde que tenía*/
        jBBusc.setBorder(bBordOri);

    }//GEN-LAST:event_jBBuscMouseExited

    
    /*Cuando se presiona el botón de búscar*/
    private void jBBuscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscActionPerformed

        /*Si el campo de buscar esta vacio no puede seguir*/
        if(jTBusc.getText().compareTo("")==0)
        {
            /*Coloca el borde rojo*/
            jTBusc.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED));

            /*Mensajea*/
            JOptionPane.showMessageDialog(null, "El campo de búsqueda esta vacio.", "Campo Vacio", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconAd)));

            /*Coloca el foco del teclado en el campo de búsqueda y regresa*/
            jTBusc.grabFocus();
            return;
        }
        
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        /*Borra todos los item en la tabla de almacenes*/
        DefaultTableModel dm = (DefaultTableModel)jTab.getModel();
        dm.setRowCount(0);

        /*Resetea el contador de filas*/
        iContFi              = 1;

        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;

        /*Obtiene de la base EL el filtrado necesario*/
        try
        {
            
            sQ = "Select referencia, descrip,zona, sucursal,clasificacion,responsable from"+
                 " activos "+
                 " WHERE referencia LIKE('%" + jTBusc.getText().replace(" ", "%") + "%')"+ 
                 " OR descrip LIKE('%" + jTBusc.getText().replace(" ", "%") + "%')"+
                 " OR zona LIKE('%" + jTBusc.getText().replace(" ", "%") + "%')"+
                 " OR sucursal LIKE('%" + jTBusc.getText().replace(" ", "%") + "%')"+
                 " OR clasificacion LIKE('%" + jTBusc.getText().replace(" ", "%") + "%')"+
                 " OR responsable LIKE('%" + jTBusc.getText().replace(" ", "%") + "%')";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos*/
            while(rs.next())
            {
                /*Agregalos en la tabla*/
                DefaultTableModel te    = (DefaultTableModel)jTab.getModel();
               Object nu[]         = {iContFi, rs.getString("referencia"), rs.getString("descrip"), rs.getString("zona"), rs.getString("sucursal"), rs.getString("clasificacion"), rs.getString("responsable")};
                te.addRow(nu);

                /*Aumenta en uno el contador de filas*/
                ++iContFi;
            }
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }

        //Cierra la base de datos
        if(Star.iCierrBas(con)==-1)
            return;

        /*Vuelve a poner el foco del teclaod en el campo de buscar*/
        jTBusc.grabFocus();

    }//GEN-LAST:event_jBBuscActionPerformed

    
    /*Cuando se presiona una tecal en el botón de búscar*/
    private void jBBuscKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBBuscKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jBBuscKeyPressed

    
    /*Cuando el mouse entra en el botón de mostrar todo*/
    private void jBMosTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBMosTMouseEntered

        /*Cambia el color del fondo del botón*/
        jBMosT.setBackground(Star.colBot);

    }//GEN-LAST:event_jBMosTMouseEntered

    
    /*Cuando el mouse sale del botón de mostrar todo*/
    private void jBMosTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBMosTMouseExited

        /*Cambia el color del fondo del botón al original*/
        jBMosT.setBackground(Star.colOri);

    }//GEN-LAST:event_jBMosTMouseExited

    
    /*Cuando se presiona el botón de mostrar todo*/
    private void jBMosTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMosTActionPerformed

        /*Función para cargar nuevamente todos los registros en la tabla*/
        vCarg();

    }//GEN-LAST:event_jBMosTActionPerformed

    
    /*Cuando se presiona una tecal en el botón de mostrar todo*/
    private void jBMosTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBMosTKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jBMosTKeyPressed

    
    /*Cuando se gana el foco del teclado en el campo de búscar*/
    private void jTBuscFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBuscFocusGained

        /*Establece el botón por default*/
        this.getRootPane().setDefaultButton(jBBusc);
        
        /*Selecciona todo el texto cuando gana el foco*/
        jTBusc.setSelectionStart(0);jTBusc.setSelectionEnd(jTBusc.getText().length());

    }//GEN-LAST:event_jTBuscFocusGained

    
    /*Cundo se pierde el foco del teclado en el campo de búscar*/
    private void jTBuscFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBuscFocusLost

        /*Establece el botón por default*/
        this.getRootPane().setDefaultButton(jBNew);
        
        /*Coloca el cursor al principio del control*/
        jTBusc.setCaretPosition(0);

        /*Coloca el borde negro si tiene datos, caso contrario de rojo*/
        if(jTBusc.getText().compareTo("")!=0)
            jTBusc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,255)));

    }//GEN-LAST:event_jTBuscFocusLost

    
    /*Cuando se presiona una tecla en el campo de búscar*/
    private void jTBuscKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTBuscKeyPressed

        /*Si se presionó enter entonces presiona el botón de búsqueda y regresa*/
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            jBBusc.doClick();
            return;
        }

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jTBuscKeyPressed

    private void jTSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTSucursalActionPerformed

    private void jCResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCResponsableActionPerformed
          /*Si no hay datos entonces regresa*/
        if(jCResponsable.getSelectedItem()==null)
            return;
        
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;

        /*Obtiene la descripción de la talla en base a su código*/
        try
        {
            sQ = "SELECT descrip FROM responsable WHERE cod = '" + jCResponsable.getSelectedItem().toString().trim() + "'";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces coloca el valor en el campo*/
            if(rs.next())
                jTResponsable.setText(rs.getString("descrip"));
            else
                jTResponsable.setText("");
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }

        /*Coloca al principio del control el caret*/
        jTResponsable.setCaretPosition(0);

        //Cierra la base de datos
        Star.iCierrBas(con);
        // TODO add your handling code here:
    }//GEN-LAST:event_jCResponsableActionPerformed

    private void jCResponsableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCResponsableKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCResponsableKeyPressed

    private void jCClasificacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCClasificacionFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jCClasificacionFocusLost

    private void jCClasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCClasificacionActionPerformed
         /*Si no hay datos entonces regresa*/
        if(jCClasificacion.getSelectedItem()==null)
            return;
        
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;

        /*Obtiene la descripción de la talla en base a su código*/
        try
        {
            sQ = "SELECT descrip FROM clasificacion WHERE cod = '" + jCClasificacion.getSelectedItem().toString().trim() + "'";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces coloca el valor en el campo*/
            if(rs.next())
                jTClasificacion.setText(rs.getString("descrip"));
            else
                jTClasificacion.setText("");
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }

        /*Coloca al principio del control el caret*/
        jTClasificacion.setCaretPosition(0);

        //Cierra la base de datos
        Star.iCierrBas(con);
    }//GEN-LAST:event_jCClasificacionActionPerformed

    private void jCClasificacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCClasificacionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCClasificacionKeyPressed

    private void jTClasificacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTClasificacionFocusGained
         /*Selecciona todo el texto cuando gana el foco*/
        jTClasificacion.setSelectionStart(0);jTClasificacion.setSelectionEnd(jTClasificacion.getText().length());
    }//GEN-LAST:event_jTClasificacionFocusGained

    private void jTClasificacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTClasificacionFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTClasificacionFocusLost

    private void jTClasificacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTClasificacionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTClasificacionKeyPressed

    private void jTResponsableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTResponsableFocusGained
       jTResponsable.setSelectionStart(0);jTResponsable.setSelectionEnd(jTResponsable.getText().length());
    }//GEN-LAST:event_jTResponsableFocusGained

    private void jTResponsableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTResponsableFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTResponsableFocusLost

    private void jTResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTResponsableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTResponsableActionPerformed

    private void jTResponsableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTResponsableKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTResponsableKeyPressed

    private void jTZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTZonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTZonaActionPerformed

    private void jTClasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTClasificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTClasificacionActionPerformed
    
    
    /*Función escalable para cuando se presiona una tecla en el módulo*/
    void vKeyPreEsc(java.awt.event.KeyEvent evt)
    {
        /*Pon la bandera para saber que ya hubó un evento y no se desloguie*/
        bIdle   = true;
        
        /*Si se presiona la tecla de escape presiona el botón de salir*/
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE) 
            jBSal.doClick();
        /*Else if se presiona Alt + T entonces presiona el botón de marcar todo*/
        else if(evt.isAltDown() && evt.getKeyCode() == KeyEvent.VK_T)
            jBTod.doClick();
        /*Else if se presiona Alt + F4 entonces presiona el botón de salir*/
        else if(evt.isAltDown() && evt.getKeyCode() == KeyEvent.VK_F4)
            jBSal.doClick();        
        /*Else if se F4 entonces presiona el botón de mostrar todos los registrod*/
        else if(evt.getKeyCode() == KeyEvent.VK_F4)
            jBMosT.doClick();        
        /*Else if se F3 entonces presiona el botón de búscar todos los registrod*/
        else if(evt.getKeyCode() == KeyEvent.VK_F3)
            jBBusc.doClick();        
    }
    
    /*Carga las marcas en el combo*/        
    private void vCargarZonas(Connection con)
    {
        /*Borra los items en el combobox*/
        jCZona.removeAllItems();
        
        /*Agrega cadena vacia*/
        jCZona.addItem("");
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;                 
        
        /*Obtiene las marcas y cargalas en el combo*/        
        try
        {
            sQ = "SELECT cod FROM zona";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces cargalas en el conrol*/
            while(rs.next())
                jCZona.addItem(rs.getString("cod"));            
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);            
        }    
        
        
    }/*Fin de private void vCargarZonas(Connection con)*/
    
     private void vCargarClasificaciones(Connection con)
    {
        /*Borra los items en el combobox*/
        jCClasificacion.removeAllItems();
        
        /*Agrega cadena vacia*/
        jCClasificacion.addItem("");
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;                 
        
        /*Obtiene las marcas y cargalas en el combo*/        
        try
        {
            sQ = "SELECT cod FROM clasificacion";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces cargalas en el conrol*/
            while(rs.next())
                jCClasificacion.addItem(rs.getString("cod"));            
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);            
        }    
        
        
    }/*Fin de private void vCargarZonas(Connection con)*/
    
      private void vCargarResponsables(Connection con)
    {
        /*Borra los items en el combobox*/
        jCResponsable.removeAllItems();
        
        /*Agrega cadena vacia*/
        jCResponsable.addItem("");
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;                 
        
        /*Obtiene las marcas y cargalas en el combo*/        
        try
        {
            sQ = "SELECT cod FROM responsable";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces cargalas en el conrol*/
            while(rs.next())
                jCResponsable.addItem(rs.getString("cod"));            
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);            
        }    
        
        
    }/*Fin de private void vCargarZonas(Connection con)*/
     
    /*Carga todos los Modelos en el combo*/        
    private void vCargaSucursales()
    {
         Connection  con = Star.conAbrBas(true, false); 
        /*Borra los items en el combobox*/
        jCSucursal.removeAllItems();
        
        /*Agrega cadena vacia*/
        jCSucursal.addItem("");
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;                 
        
        /*Obtiene todos los Modelos de la base de datos*/        
        try
        {
            sQ  = "SELECT cod FROM sucursal WHERE codZona= '"+jCZona.getSelectedItem().toString().trim()  +"'";
            st  = con.createStatement();
            rs  = st.executeQuery(sQ);
            /*Si hay datos entonces cargalos en el combo*/
            while(rs.next())
                jCSucursal.addItem(rs.getString("cod"));
            
        }
        catch(SQLException expnSQL)
        {
                /*Agrega cadena vacia*/
            jCSucursal.addItem("");
        }    
        
    }/*Fin de private void vCargaSucursales(Connection con)*/
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBusc;
    private javax.swing.JButton jBDel;
    private javax.swing.JButton jBMosT;
    private javax.swing.JButton jBNew;
    private javax.swing.JButton jBSal;
    private javax.swing.JButton jBTod;
    private javax.swing.JComboBox jCClasificacion;
    private javax.swing.JComboBox jCResponsable;
    private javax.swing.JComboBox jCSucursal;
    private javax.swing.JComboBox jCZona;
    private javax.swing.JLabel jLAyu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jP1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTBusc;
    private javax.swing.JTextField jTClasificacion;
    private javax.swing.JTextField jTDescripcion;
    private javax.swing.JTextField jTReferencia;
    private javax.swing.JTextField jTResponsable;
    private javax.swing.JTextField jTSucursal;
    private javax.swing.JTextField jTZona;
    private javax.swing.JTable jTab;
    // End of variables declaration//GEN-END:variables

}/*Fin de public class Clientes extends javax.swing.JFrame */
