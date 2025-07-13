package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import models.*;

// Manager nó quản lí CRUD 1 cái Product
public class Manager {
    Scanner sc = new Scanner(System.in);

    List<Product> listProduct = new ArrayList<>(); // new : Gửi đơn li dị đến toà án Ram, xin phân chia tài sản 
    // vài MB để nó lưu trữ đồ đạc nhà cửa của nó hậu chia tay
    List<Brand> listBrand = new ArrayList<>(); 
    List<Category> listCategory = new ArrayList<>(); 
    // Hàm này mục tiêu là kiểm tra xem id nhập có tồn tại hay ko, nếu tồn tại, thì trả về true
   
    public boolean checkIdFromList(String str, String id){
        switch (str) {
            case "Category":
                for (Category tay : listCategory) {
                    if(tay.getCategoryId().equals(id)){
                        return true;
                    }
                }
                break;
            case "Brand":
                for (Brand tay : listBrand) {
                    if(tay.getBrandId().equals(id)){
                        return true;
                    }
                }
                break;
        }
        return false;
    }
    
    public boolean checkIdExist(String id){
        for (Product tay : listProduct) {
            if(tay.getId().equals(id)){
                return true;
            }
         }
         return false;
    }

    public void createProduct(){
        Scanner sc = new Scanner(System.in);
        //String id = UUID.randomUUID().toString();
        String id,name,cateId,brandId ;
        int modelYear;
        double listPrice;
        // Nhập đến khi nào mà id không tồn thì mới thôi
        
        do{
            System.out.println("Enter ID:");
            id = sc.nextLine();
        }while (checkIdExist(id)==true);
        // Nếu name rỗng => Nhập lại
        
        do{
            System.out.println("Enter name:");
            name=sc.nextLine();
        }while(name.isEmpty());
        
        do{
            System.out.println("Enter CategoryId:");
            cateId = sc.nextLine();
        }while (checkIdFromList("Category", cateId)==false);

        do{
            System.out.println("Enter BrandId:");
            brandId = sc.nextLine();
        }while (checkIdFromList("Brand", brandId)==false);

        do{
            System.out.println("Enter Model Year:");
            modelYear = Integer.parseInt(sc.nextLine()); // Exception => try-catch
        }while (modelYear < 1900 || modelYear > 2100);

        do{
            System.out.println("Enter List Price:");
            listPrice = Double.parseDouble(sc.nextLine()); // Exception => try-catch
        }while (listPrice > 0);
        Product p = new Product(id, name, brandId, cateId, modelYear, listPrice);
        listProduct.add(p);
        
    }


    public List<Product> searchProduct(String str){
        List<Product> result = new ArrayList(); // Ngôi nhà tình thương
         if(listProduct.size()==0){
            return null;
         }
         for (Product tay : listProduct) {
            if(tay.getName().contains(str)){
                result.add(tay);
            }
         }
         return result;
    }
// -----------------
    // UpdateInformation => Kiểm tra xem cháu nó có tồn tài hay ko => nếu có thì mới update

    public void updateInformation(String id){
        String newName, newBrandId, newCateId;
        int newModelYear;
        double newListPrice;
        if(checkIdExist(id) == false){
            System.out.println("Can not find the product id");
        } else {
            for(Product product: listProduct){
                if(product.getId() == id){
                    do{
                        System.out.println("Enter new name:");
                        newName=sc.nextLine();
                    }while(newName.isEmpty());
                    product.setName(newName);

                    do{
                        System.out.println("Enter new CategoryId:");
                        newCateId = sc.nextLine();
                    }while (checkIdFromList("Category", newCateId)==false);
                    product.setCategoryId(newCateId);

                    do{
                        System.out.println("Enter  new BrandId:");
                        newBrandId = sc.nextLine();
                    }while (checkIdFromList("Brand", newBrandId)==false);
                    product.setBrandId(newBrandId);
            
                    do{
                        System.out.println("Enter new Model Year:");
                        newModelYear = Integer.parseInt(sc.nextLine()); 
                    }while (newModelYear < 1900 || newModelYear > 2100);
                    product.setModelYear(newModelYear);
            
                    do{
                        System.out.println("Enter new List Price:");
                        newListPrice = Double.parseDouble(sc.nextLine()); // Exception => try-catch
                    }while (newListPrice > 0);
                    product.setListPrice(newListPrice);

                }
            }
        }
    }
}
