package listingsreport.model

/**
 * @author Santosh
 */
trait ListingsReader {

  def readListings(): Seq[Listings]

}

case class Listings(id: Int, make: String, price: Int, mileage: String, seller_type: String)