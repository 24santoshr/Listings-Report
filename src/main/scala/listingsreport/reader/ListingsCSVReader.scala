package listingsreport.reader

import listingsreport.model.{Listings, ListingsReader}

import scala.io.Source

/**
 * @author Santosh
 */

/**
 * reads lineitems from Listings.csv
 * @param filename: Listings.csv
 */
class ListingsCSVReader(val filename: String) extends ListingsReader {

  override def readListings(): Seq[Listings] = {

    for {
      line <- Source.fromFile("listings.csv").getLines().drop(1).toVector
      values = line.split(",").map(_.trim)
    } yield Listings(values(0).toInt, values(1), values(2).toInt, values(3), values(4))
  }
}
