// Define a class representing a Person
class Person {
    // Properties
    firstName: string;
    lastName: string;
    
    // Constructor
    constructor(firstName: string, lastName: string) {
      this.firstName = firstName;
      this.lastName = lastName;
    }
    
    // Method to get full name
    getFullName(): string {
      return `${this.firstName} ${this.lastName}`;
    }
  }
  
  // Create an instance of Person
  const johnDoe = new Person("John", "Doe");
  
  // Output the full name
  console.log(johnDoe.getFullName()); // Output: John Doe
  