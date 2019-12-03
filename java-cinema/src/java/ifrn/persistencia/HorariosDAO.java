package ifrn.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ifrn.modelo.Horarios;

public class HorariosDAO {

    private static HorariosDAO instancia =new HorariosDAO();

    public static HorariosDAO getInstancia() {
        return instancia;
    }

    private HorariosDAO() {
    }

    public int grava(Horarios horario) {

        int n = 0;
        Connection conn = Conexao.getInstancia().criaConexao();

        if (conn != null) {
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(
                        "INSERT INTO horarios (id, dia, hora, preco)" +
                        " VALUES (?, ?, ?, ?)");
                pstmt.setInt(1, horario.getId());
                pstmt.setString(2, horario.getDia());
                pstmt.setString(3, horario.getHora());
                pstmt.setFloat(4, horario.getPreco());
                
                n = pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Inclusao Falhou!!!\n" + e.getMessage());
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return n;

    }

    public List<Horarios> leTodos() {

        Statement stmt = null;
        List<Horarios> listaHorarios = new ArrayList<>();
        Connection conn = Conexao.getInstancia().criaConexao();

        if (conn != null) {
            try {
                stmt = conn.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(
                        "SELECT * FROM horario ORDER BY dia DESC");
                while (rs.next()) {
                    Horarios horario = new Horarios();
                    horario.setId(rs.getInt("id"));
                    horario.setDia(rs.getString("dia"));
                    horario.setHora(rs.getString("hora"));
                    horario.setPreco(rs.getFloat("preco"));
                        
                    listaHorarios.add(horario);
                }
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return listaHorarios;
    }

    public Horarios le(int id) {

        Statement stmt = null;
        Horarios horario = null;
        Connection conn = Conexao.getInstancia().criaConexao();
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                        "SELECT * FROM horarios WHERE id = "+id);
                if (rs.next()) {
                    horario = carregaDadosNoObjeto(rs);
                } else {
                    horario = null;
                }
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        return horario;

    }

    private Horarios carregaDadosNoObjeto(ResultSet rs) throws SQLException {

        Horarios horarios = new Horarios();
        horarios.setId(rs.getInt("id"));
        horarios.setDia(rs.getString("dia"));
        horarios.setHora(rs.getString("hora"));
        horarios.setPreco(rs.getFloat("preco"));
        
        return horarios;

    }

    public int apaga(String id) {

        int n = 0;
        Statement stmt = null;
        Connection conn = Conexao.getInstancia().criaConexao();

        if (conn != null) {
            try {
                stmt = conn.createStatement();
                n = stmt.executeUpdate(
                        "DELETE FROM horarios WHERE id = " + id);
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return n;

    }

    public int altera(Horarios horarios) {

        int n = 0;
        Connection conn = Conexao.getInstancia().criaConexao();

        if (conn != null) {
            PreparedStatement ps = null;
            try {
                ps = conn.prepareStatement(
                        "UPDATE horarios SET " +
                        "dia = ?, hora=?, preco=? " +
                        "WHERE id = ?");
                ps.setString(1, horarios.getDia());
                ps.setString(2, horarios.getHora());
                ps.setString(3, horarios.getPrecoString());
                ps.setInt(4, horarios.getId());
                
                n = ps.executeUpdate();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return n;

    }

}