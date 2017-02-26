package de.cweyermann.btc.server.boundary.tpfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.cweyermann.btc.server.entity.AbstractEntityWithChilds;

public abstract class AbstractGetChilds<T extends AbstractEntityWithChilds>
        extends AbstractTpFileControl {

    public AbstractGetChilds(Connection connection) {
        super(connection);
    }

    protected T convert(ResultSet result, T groups) {
        try {
            while (result.next()) {
                groups.addChilds(result.getInt("id"), result.getString("name"));
            }
        } catch (SQLException e) {
            throw new TpFileConnectionInvalid(e);
        }
        return groups;
    }
}
