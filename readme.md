## NOTES

* Constraints can be defined under @Table annotation
* @Embeddable and @Embedded:
  * Student class should not have guardian details. So, we can define a class for guardian with the required attributes however, we need to refer this class from Student class.
  * Here, the Guardian class is defined as @Embeddable and its instance is defined as @Embedded in the Student class.
* @Modifying & @Transactional:
  * Without @Modifying and @Transactional, the update is done but with errors.
  * So, these two annotation should be used when there is an update to the table.
* Exclude the relationship attribute from the class to understand the actual behavior of the relationship.
  * The relationship attribute e.g. inverse class's instance present in owner class should be excluded from the 'toString' method in owner class.
  * This is because, if the attribute is included as part of toString(), this will be fetched all the time (even if the fetch type is LAZY).
    * <pre>
       @ToString(exclude = "userManual")
       public class Product {
    </pre>
  

## Relationships covered in this project
### Product  ----- One to One (Unidirectional)----- UserManual
<i>
A Product can exist (has a meaning) without a ProductManual whereas a ProductManual can not exist (meaningless) without a Product</i>

Both are linked to each other where one ProductManual is mapped to one product at any given moment.
<pre>

PRODUCT
-------
@OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
)
@JoinColumn(
        name="fk_user_manual_id"
)
private UserManual userManual;


USER-MANUAL
--------------
[No Change]

</pre>

* 'Product' is the Owner side in this One-To-One relationship. 'UserManual' is not aware of this relationship as this is unidirectional one-to-one.
* The owner class contains the mapping column (new column - foreign key) using the @JoinColumn annotation. So, this class gets the new column generated for it whereas the inverse class does not get any new column added.
* The inverse class does not contain anything for this relationship.
* When owner class is saved, the inverse class is also saved because of the 'Cascade.ALL' property.
* When owner class instance is queried, the inverse class instance is either lazily fetched or eagerly fetched based on the 'fetchType' attribute in the owner class.
  * LAZY Fetching:
    * Inverse class is not fetched while fetching owner class.
    * When the inverse class attribute (present in owner class) is accessed, then inverse class is fetched.
  * ### N+1 Problem:
    * There may be N+1 queries to fetch the inverse class when the inverse class is fetched individually after fetching the owner class.
    * Imagine we want to fetch a list of `Product`s, where each product has a `UserManual`. 
    * This results in 1 query for fetching the list of products and then 1 query to fetch user manual for each product (N queries for N products). So, N+1.
    * This is not an efficient way in this scenario. We should have only one query which joins both owner and inverse class. This is done by the below mechanism in the repository class for owner class.
      <pre>
      @EntityGraph(attributePaths = "userManual")
      List<Product> findAll();
      </pre>
    * So, with this, the inverse class ('UserManual') is not fetched using individual queries rather using one query with join.
    * NOTE:
      * This solution should only be used when we want to fetch both the owner class and the inverse class together.
      * This should not be used in case of pure LAZY loading.
  * EAGER Fetching:
    * Inverse class is also fetched while fetching owner class at the same time.
    * In this activity, if one query (with join) is used or multiple queries are used, depends on the usage of `@EntityGraph` configuration. Check the below table to get more details.

  * | Fetch Type | Without `@EntityGraph` | With `@EntityGraph` |
    |------------|-------------------------|----------------------|
    | **EAGER**  | - Executes one query to fetch all instances of the `owner` class.<br> - Executes an additional query per `owner` instance to fetch associated `inverse` class data (fetched simultaneously with the owner). | - Executes only **one query** with a **JOIN** to fetch both `owner` and `inverse` classes together.<br> - Optimized for a single query, preventing additional queries for each associated entity. |
    | **LAZY**   | - Executes **one query** to fetch all instances of the `owner` class.<br> - Executes an individual query for each `inverse` class when the association is accessed for the first time (fetches are deferred until access). | - Executes only **one query** with a **JOIN** to fetch both `owner` and `inverse` classes together.<br> - Optimized for a single query, preventing additional queries for each associated entity. |

* When inverse class instance is queried, no owner class instance is fetched as there is no attribute for 'owner class' present in the inverse class because of uni-directional relationship.

### Employee  ----- One to One (Bi-directional)----- Laptop
<i>
An Employee can exist (has a meaning) without a Laptop and a Laptop can also exist (has a meaning) without a Employee.

However, both are linked to each other where one Laptop is mapped to one Employee at any given moment.
</i>


<pre>
EMPLOYEE
---------
@OneToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
)
@JoinColumn(
        name="fk_laptop_id"
)
private Laptop laptop;


LAPTOP
-------
@OneToOne(
        mappedBy = "laptop",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
)
private Employee employee;

</pre>

* PFB the points:
  * 'Employee' is the Owner side and 'Laptop' is the inverse side in this One-To-One bidirectional relationship.
  * The owner class contains the mapping column (new column - foreign key) using the @JoinColumn annotation. So, this class gets the new column generated for it whereas the inverse class does not get any new column added.
  * The inverse class contains the 'mappedBy' attribute which refers to the join column in the owner class.
  * When any one of the owner class or inverse class instance is saved, the other is also saved because of the 'Cascade.ALL' property in each of these classes.
  * When owner class instance is queried, the inverse class instance is either lazily fetched or eagerly fetched based on the 'fetchType' attribute in the owner class.
    * LAZY fetching : -- <i>SAME AS UNIDIRECTIONAL RELATIONSHIP</i> --
    * EAGER Fetching : -- <i>SAME AS UNIDIRECTIONAL RELATIONSHIP</i> --
  * When inverse class instance is queried, the owner class instance is always eagerly fetched.
    * EAGER fetching: -- <i>SAME AS UNIDIRECTIONAL RELATIONSHIP</i> --
    * Why this is EAGER ?
      * Though the inverse class contains a mapping attribute for owner class however there is no foreign key column present in this inverse class.
      * As we know, when we query the inverse class instance using LAZY fetch, Hibernate needs to create a proxy object for the owner class attribute however, it doesn't know if the record exists in owner class table (because of no foreign key in inverse class)
      * So, even to create this proxy object, the owner class table needs to be queried to get the owner instance's primary key.
      * Hence, this eventually becomes an EAGER fetch though we configure it as LAZY.

### House  ----- One to One - using 3rd table (Uni / Bi-directional)----- ParkingSpace
<i>
A House can exist (has a meaning) without a ParkingSpace and a ParkingSpace can also exist (has a meaning) without a House.

However, both are linked to each other where one ParkingSpace is mapped to one House at any given moment.
</i>

<pre>

HOUSE
---------
@OneToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
)
@JoinTable(
        name = "houseParking",
        joinColumns = {@JoinColumn(name = "house_id") },
        inverseJoinColumns = {@JoinColumn(name = "parking_id") }
)
private ParkingSpace parkingSpace;


PARKING-SPACE
--------------
@OneToOne(
        mappedBy = "parkingSpace",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
)
private House house;

</pre>

* PFB the points:
  * Here the class which defines the @JoinTable is the owner side and the other class is inverse side. 
    * 'joinColumns' contains the column of owner class and 'inverseJoinColumns' contains the column of inverse class. 
  * One class contains the relationship using @JoinTable annotation and the other class contains the 'mappedBy' attribute which refers to the join column in the former class.
    * With this, no extra column (mapping column) is created in any of these tables rather a new mapping table is created referring to both these table's primary key. 
  * When instance of any one of these classes is saved, the other is also saved because of the 'Cascade.ALL' property.
    * The instances of the individual classes is saved in respective tables.
    * An entry goes to the mapping table for the mapping between these two instances.
  * When instance of any of these classes is queried, the other class instance is either lazily fetched or eagerly fetched based on the 'fetchType' attribute in the queried class.
    * LAZY fetching : 
      * Instance of the first class is fetched from the respective table along with JOIN to the mapping table to create the proxy object for the second class.
      * Second class's table is not queried during this time.
      * Whenever we try to access second class's attributes, the second class is queried.
    * EAGER Fetching : -- <i>SAME AS UNIDIRECTIONAL RELATIONSHIP</i> --

### Author  ----- One to Many - (Bi-directional)----- Book
<i>
An author can write multiple books so there is a OneToMany relationship between Author and Book.
In the other words, many books can be written by a single author. So, there is a ManyToOne relationship between Book and Author.
</i>

<pre>

AUTHOR
---------
@OneToMany(
        mappedBy = "author",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
)
private List<Book> books;


BOOK
--------------
@ManyToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER
)
@JoinColumn(
        name="fk_author_id"
)
private Author author;


</pre>

* PFB the points:
  * Here 'Book' is the owner side and 'Author' is the inverse side.
  * The 'Book' class has a @ManyToOne relationship to 'Author' using @JoinColumn annotation and the 'Author' class contains the 'mappedBy' attribute along with @OneToMany relationship with 'Book' class.
    * With this, an extra column (mapping column) is created in 'Book' class.
  * When instance of any one of these classes is saved, the other is also saved because of the 'Cascade.ALL' property.
  * When instance of the owner (@ManyToOne) class is queried, it works as mentioned below:
    * LAZY fetching : Only Owner class is fetched. [ This is expected as we already have the foreign key reference of inverse class for creating its proxy]</b>
    * EAGER fetching : Owner class is fetched along with mapping table as well as inverse class.
  * When instance of the inverse (@OneToMany) class is queried, it works as mentioned below:
    * LAZY fetching : Only the inverse class is fetched. <b> [ This may be because of pure lazy loading logic for performance reasons to avoid fetching the list of owner classes ]</b>
    * EAGER fetching : Inverse class is fetched along with owner class.



### School  ----- One to Many - using 3rd table (Bi-directional)----- Teacher
<i>
A school can have multiple teachers so there is a OneToMany relationship between School and Teacher.
In the other words, many teachers can be a part of one school. So, there is a ManyToOne relationship between Teacher and School.
</i>
<pre>

SCHOOL
---------
@OneToMany(
        mappedBy = "school",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
)
private List<Teacher> teachers;


TEACHER
--------------
@ManyToOne(
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
)

@JoinTable(
    name="school_teachers",
    joinColumns = {@JoinColumn(name = "teacher_id")},
    inverseJoinColumns = {@JoinColumn(name = "school_id")}
)

private School school;

</pre>

* PFB the points:
  * Here the class which defines the @JoinTable is the owner side and the other class is inverse side.
    * 'joinColumns' contains the column of owner class and 'inverseJoinColumns' contains the column of inverse class.
  * One class contains the relationship using @JoinTable annotation and the other class contains the 'mappedBy' attribute which refers to the join column in the former class.
    <pre>
    @ManyToOne annotation does not have 'mappedBy' attribute. 
    So, it can only be in the owner class whereas the @OneToMany is in the inverse class. 
    </pre>
    * With this, no extra column (mapping column) is created in any of these tables rather a new mapping table is created referring to both these table's primary key.
  * When instance of any one of these classes is saved, the other is also saved because of the 'Cascade.ALL' property.
    * The instances of the individual classes is saved in respective tables.
    * An entry goes to the mapping table for the mapping between these two instances.
  * When instance of the owner (@ManyToOne) class is queried, it works as mentioned below:
    * LAZY fetching : Owner class is fetched along with JOIN to the mapping table to create the proxy object for the inverse class.
    * EAGER fetching : Owner class is fetched along with mapping table as well as inverse class.
  * When instance of the inverse (@OneToMany) class is queried, it works as mentioned below:
    * LAZY fetching : Only the inverse class is fetched. Neither the mapping class nor the owner class is fetched. <b> [ This may be because of pure lazy loading logic for performance reasons to avoid fetching the list of owner classes. Here even the mapping table is also not used unless owner class reference is desired ]</b>
    * EAGER fetching : Inverse class is fetched along with mapping table as well as owner class.

### Customer  ----- Many to Many - using 3rd table (Bi-directional)----- Item
<i>
A school can have multiple teachers so there is a OneToMany relationship between School and Teacher.
In the other words, many teachers can be a part of one school. So, there is a ManyToOne relationship between Teacher and School.
</i>
<pre>

CUSTOMER
---------
@ManyToMany(
mappedBy = "customers",
cascade = CascadeType.ALL,
fetch = FetchType.LAZY
)
private List<Item> items;


ITEM
--------------
@ManyToMany(
cascade = CascadeType.ALL,
fetch = FetchType.LAZY
)
@JoinTable(
name="customer_items",
joinColumns = {@JoinColumn(name = "item_id")},
inverseJoinColumns = {@JoinColumn(name = "customer_id")}
)
private List<Customer> customers;

</pre>

* PFB the points:
  * Here the class which defines the @JoinTable is the owner side and the other class is inverse side.
    * 'joinColumns' contains the column of owner class and 'inverseJoinColumns' contains the column of inverse class.
  * One class contains the relationship using @JoinTable annotation and the other class contains the 'mappedBy' attribute which refers to the join column in the former class.
     * With this, no extra column (mapping column) is created in any of these tables rather a new mapping table is created referring to both these table's primary key.
  * When instance of any one of these classes is saved, the other is also saved because of the 'Cascade.ALL' property.
    * The instances of the individual classes is saved in respective tables.
    * An entry goes to the mapping table for the mapping between these two instances.
  * When instance of the owner class is queried, it works as mentioned below:
    * LAZY fetching : Only Owner class is fetched. Neither the mapping table nor the inverse class is fetched. <b> [ This may be because of pure lazy loading logic for performance reasons to avoid fetching the list of owner classes. Here even the mapping table is also not used unless owner class reference is desired ]</b>
    * EAGER fetching : Owner class is fetched along with mapping table as well as inverse class.
  * When instance of the inverse class is fetched, it works as mentioned below:
    * LAZY fetching : Only the inverse class is fetched. Neither the mapping table nor the owner class is fetched. <b> [ This may be because of pure lazy loading logic for performance reasons to avoid fetching the list of owner classes. Here even the mapping table is also not used unless owner class reference is desired ]</b>
    * EAGER fetching : Inverse class is fetched along with mapping table as well as owner class.




# Special Notes on LAZY fetching:
* With a 3rd table:
  * OWNER class :
    * @OneToOne / @ManyToOne : Owner + Joining table is considered. So, LAZY fetching works as expected and the inverse table is not queried.
    * @ManyToMany : Only owner table is considered. -- [As there is a list reference to the inverse class, this might be done because of performance reasons]
  * INVERSE class :
    * @OneToOne / @OneToMany : Owner + Joining table is considered. So, LAZY fetching works as expected and the inverse table is not queried.
    * @ManyToMany: LAZY (owner class is not joined/queried) -- [As there is a list reference to the owner class, this might be done because of performance reasons]


* Without a 3rd table:
  * OWNER class :
    * @OneToOne / @ManyToOne : Only owner table is considered. So, LAZY fetching works as expected and the inverse table is not queried. 
  * INVERSE class :
    * @OneToOne: Always ends up in EAGER, even configured as LAZY (because of proxy object creation)
    * @OneToMany: LAZY (owner class is not joined/queried) --  [As there is a list reference to the owner class, this might be done because of performance reasons]

