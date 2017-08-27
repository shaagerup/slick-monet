package slick.jdbc

import java.util.UUID
import java.sql.{PreparedStatement, ResultSet}
import slick.jdbc.JdbcDriver

import scala.concurrent.ExecutionContext
import slick.dbio._
import slick.lifted._
import slick.profile.{SqlProfile, RelationalProfile, Capability}
import slick.ast.{SequenceNode, Library, FieldSymbol, Node, Insert, InsertColumn, Select, ElementSymbol, ColumnOption }
import slick.ast.Util._
import slick.util.MacroSupport.macroSupportInterpolation
import slick.compiler.CompilerState
import slick.jdbc.meta.{MIndexInfo, MColumn, MTable}
import slick.jdbc.{JdbcModelBuilder, JdbcType}
import slick.model.Model


trait MonetDriver extends JdbcDriver { driver =>

  class QueryBuilder(tree: Node, state: CompilerState) extends super.QueryBuilder(tree, state) {
    override protected def buildFetchOffsetClause(fetch: Option[Node], offset: Option[Node]) = (fetch, offset) match {
      case (Some(t), Some(d)) => b" limit $t offset $d"
      case (Some(t), None   ) => b" limit $t"
      case (None,    Some(d)) => b" limit 9223372036854775805 offset $d" // 2^63 - 3
      case _ =>
    }
  }

  class ColumnDDLBuilder(column: FieldSymbol) extends super.ColumnDDLBuilder(column) {
    override protected def appendOptions(sb: StringBuilder) {
      if(defaultLiteral ne null) sb append " DEFAULT " append defaultLiteral
      if(notNull) sb append " NOT NULL"
      else if(sqlType.toUpperCase == "TIMESTAMP") sb append " NULL"
      if(autoIncrement) sb append " AUTO_INCREMENT"
      if(primaryKey) sb append " PRIMARY KEY"
    }
  }


  override def createQueryBuilder(n: Node, state: CompilerState): QueryBuilder = new QueryBuilder(n, state)
  override def createColumnDDLBuilder(column: FieldSymbol, table: Table[_]): ColumnDDLBuilder = new ColumnDDLBuilder(column)


}

object MonetDriver extends MonetDriver

