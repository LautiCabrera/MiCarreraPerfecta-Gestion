/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


import Utils.DDBBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Branch_Words_Key_Methods {
    
    //crear un nuevo registro (create)
    public void createBranchWordsKey(Branch_Words_Key branchWordsKey) {
        Connection connection = DDBBConnection.Conectar();
        if (connection != null) {
            try {
                String insertQuery = "INSERT INTO branch_words_key (id_branch_word_key, id_branch, id_words_key, id_career, branch_words_keycol, id_user_create, id_user_update, f_create, f_update) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(insertQuery);
                statement.setInt(1, branchWordsKey.getId_branch_word_key());
                statement.setInt(2, branchWordsKey.getId_branch());
                statement.setInt(3, branchWordsKey.getId_words_key());
                statement.setInt(4, branchWordsKey.getId_career());
                statement.setInt(5, branchWordsKey.getBranch_words_keycol());
                statement.setInt(6, branchWordsKey.getId_user_create());
                statement.setInt(7, branchWordsKey.getId_user_update());
                statement.setString(8, branchWordsKey.getF_create());
                statement.setString(9, branchWordsKey.getF_update());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Registro insertado correctamente.");
                } else {
                    System.out.println("No se pudo insertar el registro.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DDBBConnection.closeResources(connection, null, null);
            }
        }
    }

    //obtener un registro por ID (Read)
    public Branch_Words_Key getBranchWordsKeyById(int id) {
        Connection connection = DDBBConnection.Conectar();
        if (connection != null) {
            try {
                String selectQuery = "SELECT * FROM branch_words_key WHERE id_branch_word_key = ?";
                PreparedStatement statement = connection.prepareStatement(selectQuery);
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return new Branch_Words_Key(
                            resultSet.getInt("id_branch_word_key"),
                            resultSet.getInt("id_branch"),
                            resultSet.getInt("id_words_key"),
                            resultSet.getInt("id_career"),
                            resultSet.getInt("branch_words_keycol"),
                            resultSet.getInt("id_user_create"),
                            resultSet.getInt("id_user_update"),
                            resultSet.getString("f_create"),
                            resultSet.getString("f_update")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DDBBConnection.closeResources(connection, null, null);
            }
        }
        return null;
    }

    //obtener todos los registros (Read)
    public List<Branch_Words_Key> getAllBranchWordsKeys() {
        List<Branch_Words_Key> branchWordsKeys = new ArrayList<>();
        Connection connection = DDBBConnection.Conectar();
        if (connection != null) {
            try {
                String selectQuery = "SELECT * FROM branch_words_key";
                PreparedStatement statement = connection.prepareStatement(selectQuery);

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    branchWordsKeys.add(new Branch_Words_Key(
                            resultSet.getInt("id_branch_word_key"),
                            resultSet.getInt("id_branch"),
                            resultSet.getInt("id_words_key"),
                            resultSet.getInt("id_career"),
                            resultSet.getInt("branch_words_keycol"),
                            resultSet.getInt("id_user_create"),
                            resultSet.getInt("id_user_update"),
                            resultSet.getString("f_create"),
                            resultSet.getString("f_update")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DDBBConnection.closeResources(connection, null, null);
            }
        }
        return branchWordsKeys;
    }

    //actualizar un registro existente (Update)
    public void updateBranchWordsKey(Branch_Words_Key updatedBranchWordsKey) {
        Connection connection = DDBBConnection.Conectar();
        if (connection != null) {
            try {
                String updateQuery = "UPDATE branch_words_key SET id_branch = ?, id_words_key = ?, id_career = ?, branch_words_keycol = ?, id_user_create = ?, id_user_update = ?, f_create = ?, f_update = ? WHERE id_branch_word_key = ?";
                PreparedStatement statement = connection.prepareStatement(updateQuery);
                statement.setInt(1, updatedBranchWordsKey.getId_branch());
                statement.setInt(2, updatedBranchWordsKey.getId_words_key());
                statement.setInt(3, updatedBranchWordsKey.getId_career());
                statement.setInt(4, updatedBranchWordsKey.getBranch_words_keycol());
                statement.setInt(5, updatedBranchWordsKey.getId_user_create());
                statement.setInt(6, updatedBranchWordsKey.getId_user_update());
                statement.setString(7, updatedBranchWordsKey.getF_create());
                statement.setString(8, updatedBranchWordsKey.getF_update());
                statement.setInt(9, updatedBranchWordsKey.getId_branch_word_key());

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Registro actualizado correctamente.");
                } else {
                    System.out.println("No se pudo actualizar el registro.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DDBBConnection.closeResources(connection, null, null);
            }
        }
    }

    //eliminar un registro por ID (Delete)
    public void deleteBranchWordsKey(int id) {
        Connection connection = DDBBConnection.Conectar();
        if (connection != null) {
            try {
                String deleteQuery = "DELETE FROM branch_words_key WHERE id_branch_word_key = ?";
                PreparedStatement statement = connection.prepareStatement(deleteQuery);
                statement.setInt(1, id);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Registro eliminado correctamente.");
                } else {
                    System.out.println("No se pudo eliminar el registro.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DDBBConnection.closeResources(connection, null, null);
            }
        }
    }
}
