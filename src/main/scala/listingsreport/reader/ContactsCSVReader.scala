package listingsreport.reader

import listingsreport.model.{Contacts, ContactsReader}

import scala.io.Source

/**
 * @author Santosh
 */

/**
 * reads lineitems from Contacts.csv
 * @param filename: Contacts.csv
 */
class ContactsCSVReader(val filename: String) extends ContactsReader {

  override def readContacts(): Seq[Contacts] = {

    for {
      line <- Source.fromFile("contacts.csv").getLines().drop(1).toVector
      values = line.split(",").map(_.trim)
    } yield Contacts(values(0).toInt, values(1))
  }

}
