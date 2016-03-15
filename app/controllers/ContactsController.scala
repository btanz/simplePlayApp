package controllers

import play.api._
import play.api.mvc._
import models.ContactModel

class ContactsController extends Controller{
  
  def index = Action {
    
    val contacts = ContactModel.all
   
    Ok(views.html.index(contacts, ContactModel.form))
  }
  
  def create = Action { implicit request => 
    ContactModel.form.bindFromRequest.fold(
      errors => BadRequest(views.html.index(ContactModel.all, errors)),
      contact => {
        ContactModel.create(contact)
        Redirect(routes.ContactsController.index)
      }
    )  
  }
  
  def edit(id: Long) = TODO
  
  def update(id: Long) = TODO
  
  def delete(id: Long) = TODO
  
}