package org.example.datos;


import org.example.restaurante.Comanda;
import org.example.restaurante.Menu;
import org.example.restaurante.Mesa;
import org.example.restaurante.Plato;
import org.example.usuario.Cliente;
import org.example.usuario.Usuario;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VolcadoDeDatos {

    public List<Usuario> buscarUsuario(String nombreArchivo, String nombre1){
        List<Usuario> listaUsuario = new ArrayList<>();

        try (BufferedReader leer = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            leer.readLine();

            while ((linea = leer.readLine()) !=null){
                String[] datos = linea.split(",");
                if(datos.length == 7){
                    if(datos[1].toLowerCase().contains(nombre1.toLowerCase())){
                        int id_usuario = Integer.parseInt(datos[0]);
                        String nombre = datos[1];
                        String tipo = datos[2];
                        int comensales = Integer.parseInt(datos[3]);
                        int telefono = Integer.parseInt(datos[4]);
                        String email = datos[5];
                        String password = datos[6];


                        //añadir lista objeto cliente a la lista
                        Usuario usuario = new Usuario(id_usuario,nombre,tipo,comensales,telefono,email, password);
                        listaUsuario.add(usuario);
                    }
                }
            }
            System.out.println("Lista de usuario ejecutada ");
        }catch (IOException e){
            e.printStackTrace();
        }
        return listaUsuario;
    }


    public List<Cliente> buscarClientes(String nombreArchivo, String nombre1){
        List<Cliente> listaClientes = new ArrayList<>();

        try (BufferedReader leer = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            leer.readLine();

            while ((linea = leer.readLine()) !=null){
                String[] datos = linea.split(",");
                if(datos.length == 8){
                    if(datos[1].toLowerCase().contains(nombre1.toLowerCase())){
                        int id_usuario = Integer.parseInt(datos[0]);
                        String nombre = datos[1];
                        String tipo = datos[2];
                        int comensales = Integer.parseInt(datos[3]);
                        int telefono = Integer.parseInt(datos[4]);
                        String email = datos[5];
                        String password = datos[6];
                        String alergias = datos[7];

                        //añadir lista objeto cliente a la lista
                        Cliente cliente = new Cliente(id_usuario,nombre,tipo,comensales,telefono,alergias,email,password);
                        listaClientes.add(cliente);
                    }
                }
            }
            System.out.println("Lista de clientes ejecutada ");
        }catch (IOException e){
            e.printStackTrace();
        }
        return listaClientes;
    }

    public List<Menu> buscarMenu(String nombreArchivo, String nombre1){
        List<Menu> listaMenu = new ArrayList<>();

        try (BufferedReader leer = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            leer.readLine();

            while ((linea = leer.readLine()) !=null){
                String[] datos = linea.split(",");
                if(datos.length == 3){
                    if(datos[1].toLowerCase().contains(nombre1.toLowerCase())){

                        int idMenu = Integer.parseInt(datos[0]);
                        String nombre = datos[1];
                        double precio = Integer.parseInt(datos[2]);

                        //añadir lista objeto cliente a la lista
                        Menu menu = new Menu(idMenu, nombre, precio);
                        listaMenu.add(menu);
                    }
                }
            }
            System.out.println("Lista de menu ejecutada ");
        }catch (IOException e){
            e.printStackTrace();
        }
        return listaMenu;
    }

    public List<Mesa> buscarMesa(String nombreArchivo, String nombre1){
        List<Mesa> listaMesa = new ArrayList<>();

        try (BufferedReader leer = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            leer.readLine();

            while ((linea = leer.readLine()) !=null){
                String[] datos = linea.split(",");
                if(datos.length == 4){
                    if(datos[1].toLowerCase().contains(nombre1.toLowerCase())){

                        int idMesa = Integer.parseInt(datos[0]);
                        int idUsuario = Integer.parseInt(datos[1]);
                        boolean estado = Boolean.parseBoolean(datos[2]);
                        int capacidad = Integer.parseInt(datos[3]);

                        //añadir lista objeto cliente a la lista
                        Mesa mesa = new Mesa(idMesa, idUsuario, estado, capacidad);
                        listaMesa.add(mesa);
                    }
                }
            }
            System.out.println("Lista de menu ejecutada ");
        }catch (IOException e){
            e.printStackTrace();
        }
        return listaMesa;
    }

    public List<Comanda> buscarComanda(String nombreArchivo, String nombre1){
        List<Comanda> listaComanda = new ArrayList<>();

        try (BufferedReader leer = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            leer.readLine();

            while ((linea = leer.readLine()) !=null){
                String[] datos = linea.split(",");
                if(datos.length == 4){
                    if(datos[1].toLowerCase().contains(nombre1.toLowerCase())){

                        int idComanda = Integer.parseInt(datos[0]);
                        int idMesa = Integer.parseInt(datos[1]);
                        LocalDateTime hora_comanda = LocalDateTime.parse(datos[2]);
                        int turno_comanda = Integer.parseInt(datos[3]);

                        //añadir lista objeto cliente a la lista
                        Comanda comanda = new Comanda(idComanda, idMesa, hora_comanda, turno_comanda);
                        listaComanda.add(comanda);
                    }
                }
            }
            System.out.println("Lista de menu ejecutada ");
        }catch (IOException e){
            e.printStackTrace();
        }
        return listaComanda;
    }

    public List<Plato> buscarPlato(String nombreArchivo, String nombre1){
        List<Plato> listaPlato = new ArrayList<>();

        try (BufferedReader leer = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            leer.readLine();

            while ((linea = leer.readLine()) !=null){
                String[] datos = linea.split(",");
                if(datos.length == 6){
                    if(datos[1].toLowerCase().contains(nombre1.toLowerCase())){

                        int idPlato = Integer.parseInt(datos[0]);
                        int idUsuario = Integer.parseInt(datos[1]);
                        String nombre_plato = datos[2];
                        double precio = Integer.parseInt(datos[3]);
                        String alergenos = datos[4];
                        String ingredientes = datos[5];


                        //añadir lista objeto cliente a la lista
                        Plato plato = new Plato(idPlato, idUsuario, nombre_plato, precio, alergenos, ingredientes);
                        listaPlato.add(plato);
                    }
                }
            }
            System.out.println("Lista de menu ejecutada ");
        }catch (IOException e){
            e.printStackTrace();
        }
        return listaPlato;
    }






}
