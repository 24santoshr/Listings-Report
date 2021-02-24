package listingsreport

import listingsreport.model.{AverageListings, Contacts, ContactsReader, Listings, ListingsReader, PercentileDistribution}

class GetListingsReport(val listingsReader: ListingsReader, val contactsReader: ContactsReader) {

  val contacts = contactsReader.readContacts()
  val listings = listingsReader.readListings()

  /**
   * Calculates listing price by seller type
   * @return AverageListings
   */
  def getAvgListingsPricesGroupedBySellerType: Seq[AverageListings] = {

    var averageListings = Seq[AverageListings]()

    //calculates sum of each record for a particular group
    def avg(salesOfASellerType: Seq[Listings]): Double =
      salesOfASellerType.map(_.price).sum / salesOfASellerType.size

    val result = listings.groupBy(_.seller_type).mapValues(avg(_))

    result.foreach {
      getAverageListings =>
        averageListings = averageListings ++ Seq(AverageListings(getAverageListings._1, getAverageListings._2 + " €"))
    }
    averageListings
  }

  /**
   * calculate percentile distribution of cars
   * @return PercentileDistribution
   */
  def getPercentileDistributionOfCarsByMake: Seq[PercentileDistribution] = {
    var percentileDistribution = Seq[PercentileDistribution]()

    def getIndividualMakeCount(salesOfASellerType: Seq[Listings]): Double = {
      salesOfASellerType.size
    }

    def getPercentile(makeCount: Map[String, Double]): Map[String, Double] = {
      makeCount.mapValues((_ * 100)).mapValues(_ / getTotalNumberOfListings)
    }


    val individualMakeCount = listings.groupBy(_.make).mapValues(getIndividualMakeCount)

    // sorting the resultant set in descending order
    val result = getPercentile(individualMakeCount).toList.sortWith(_._2 > _._2)


    result.foreach { getDistribution =>

      percentileDistribution = percentileDistribution ++ Seq(PercentileDistribution(getDistribution._1, getDistribution + " %"))
    }
    percentileDistribution
  }

  /**
   * calculates size of Listings
   * @return
   */
  def getTotalNumberOfListings: Int = listings size

  /**
   * calculates 30% of most contacted listings
   * @return
   */
  def getMostContactListings: Int = {

    def insertContactLists(contacts: Seq[Contacts]): Int = {
      contacts.size
    }

    val sortedContacts = contacts.groupBy(_.listing_id).mapValues(insertContactLists).toList.sortWith(_._2 > _._2)

    //limits the resultant set to top 30% of the total records
    val limitedContact = sortedContacts.take((sortedContacts.size * 0.3).toInt).toMap

    var sum = 0

    for (x <- limitedContact.keys) {
      val filter = listings.filter(_.id == x)
      sum += filter.map((_.price)).sum
    }

    val averagePrice = sum / limitedContact.keys.size

    averagePrice
  }

}


