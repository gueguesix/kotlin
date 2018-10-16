package ii_collections

import ii_collections.data.*
import org.junit.Assert.assertEquals
import org.junit.Test

class N23CompoundTasksKtTest {
    @Test fun testGetCustomersWhoOrderedProduct() {
        assertEquals(setOf(customers[reka], customers[asuka]), shop.getCustomersWhoOrderedProduct(idea))
    }

    @Test fun testMostExpensiveDeliveredProduct() {
        val testShop = shop("test shop for 'most expensive delivered product'",
                customer(lucas, Canberra,
                        order(idea, isDelivered = false),
                        order(reSharper)
                )
        )
        assertEquals(reSharper, testShop.customers[0].getMostExpensiveDeliveredProduct())
    }

    fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
        // Return the set of customers who ordered the specified product
        return customers.filter { it.orderedProducts.contains(product) }.toSet()
    }

    fun Customer.getMostExpensiveDeliveredProduct(): Product? {
        // Return the most expensive product among all delivered products
        // (use the Order.isDelivered flag)
        return orders.filter { it.isDelivered }.flatMap { it.products }.maxBy { it.price }
    }

    fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
        // Return the number of times the given product was ordered.
        // Note: a customer may order the same product for several times.
        return customers.flatMap { it.orders }.flatMap { it.products }.count { it == product }
    }
}
