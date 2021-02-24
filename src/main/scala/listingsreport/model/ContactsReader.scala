package listingsreport.model

/**
 * @author Santosh
 */
trait ContactsReader {

  def readContacts(): Seq[Contacts]
}

case class Contacts(listing_id: Int, contact_date: String)