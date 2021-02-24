package listingsreport

import listingsreport.reader.{ContactsCSVReader, ListingsCSVReader}
import org.scalatest.Matchers._
import org.scalatest.{BeforeAndAfter, FunSuite}

/**
 * @author Santosh
 */
class GetListingsReportTest extends FunSuite with BeforeAndAfter {
  var reports: GetListingsReport = _


  before {
    val listingsReader = new ListingsCSVReader("listings.csv")
    val contactsReader = new ContactsCSVReader("contacts.csv")

    reports = new GetListingsReport(listingsReader, contactsReader)
  }

  test("testGetAvgListingsPricesGroupedBySellerType") {

    val getAvgPricesPerSellerType = reports.getAvgListingsPricesGroupedBySellerType

    val sellerTye = getAvgPricesPerSellerType.map(_.sellerType)

    sellerTye should contain("\"private\"")
    sellerTye should contain("\"dealer\"")
    sellerTye should contain("\"other\"")
  }

  test("testGetPercentileDistributionOfCarsByMake") {

    val getPercentileDistribution = reports.getPercentileDistributionOfCarsByMake


    val make = getPercentileDistribution.map(_.make)

    val price = getPercentileDistribution.map(_.distribution)

    make should not be empty

    price should not be empty

  }

  test("testGetMostContactListings") {

    val getMostContactListings = reports.getMostContactListings

    getMostContactListings should be
    24638

  }

}
