package models

import anorm._
import play.api.db.DB
import play.api.Play.current

case class ContactModel (id: Long, name: String, emailAddress: String)


object ContactModel {
  
  def all = {
    DB.withConnection { implicit connection =>
      
      SQL("SELECT * FROM contacts")().map { row => 
        ContactModel(
            id = row[Long]("id"),
            name = row[String]("name"),
            emailAddress = row[String]("emailAddress")
            )
        }.toList
      
    }
  }
  
  
  def create(contact: ContactModel) {
    DB.withConnection { implicit connection =>
      SQL("INSERT INTO contacts(name, emailAddres) VALUES ({name}, {emailAddress})")(
        "name" -> contact.name,
        "emailAddress" -> contact.emailAddress
    ).execute()
  }
  }
  
  // validate data
  import play.api.data._
  import play.api.data.Forms._
  
  val form = Form(
      mapping(
            "id" -> ignored(0L),
            "name" -> nonEmptyText,
            "emailAdress" -> email
            )(ContactModel.apply)(ContactModel.unapply)
  )
  
}