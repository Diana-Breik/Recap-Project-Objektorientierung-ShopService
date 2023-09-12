import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @org.junit.jupiter.api.Test
    void getProducts() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        List<Product> actual = repo.getProducts();

        //THEN
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("1", "Apfel"));
        assertEquals(actual, expected);
    }

    @org.junit.jupiter.api.Test
    void getProductById() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        Optional<Product> actual = repo.getProductById("1");//

        //THEN
        Product expected = new Product("1", "Apfel");
        assertEquals(actual, Optional.ofNullable(expected));
    }

    @org.junit.jupiter.api.Test
    void addProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        Product newProduct = new Product("2", "Banane");

        //WHEN
        Product actual = repo.addProduct(newProduct);

        //THEN
        Product expected = new Product("2", "Banane");
        assertEquals(actual, expected);
        assertEquals(repo.getProductById("2"), Optional.of(expected));
    }

    @org.junit.jupiter.api.Test
    void removeProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        repo.removeProduct("1");
        Optional<Product> actual = repo.getProductById("1");

        //THEN
        Optional<Product> expected = Optional.empty();
        assertEquals(expected,actual);

    }

    @Test
    void getProductByID_ifFound_ThenProduct(){
        //GIVEN
        ProductRepo productRepo = new ProductRepo();
        //WHEN
        Optional<Product> actual = productRepo.getProductById("1");
        //THEN
        Product expected = new Product("1", "Apfel");
        assertEquals(Optional.ofNullable(expected),actual);//wenn ich ganz sicher bin, dass diesen Produkt da ist,  verwende ich .ofNullable(), wenn der Gegenteil ist, dass ich nicht sicher bin, dann benutze ish of.
    }

    @Test
    void getProductByID_ifNOTFound_thenEmpty(){
        //GIVEN
        ProductRepo productRepo = new ProductRepo();
        //WHEN
        Optional<Product> actual = productRepo.getProductById("2");
        //THEN
        Optional<Product> expected = Optional.empty();
        assertEquals(expected,actual);//(expected,actual)
    }

}
