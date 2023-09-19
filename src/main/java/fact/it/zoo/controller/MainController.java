package fact.it.zoo.controller;


import fact.it.zoo.model.AnimalWorld;
import fact.it.zoo.model.Staff;
import fact.it.zoo.model.Visitor;
import fact.it.zoo.model.Zoo;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
// start exam


@Controller
public class MainController{
    private ArrayList<Staff> staffArrayList;
    private ArrayList<Visitor> visitorArrayList;
    private ArrayList<Zoo> zooArrayList;

//Write your code here
    @PostConstruct
    private void fillData() {
        staffArrayList = fillStaffMembers();
        visitorArrayList = fillVisitors();
        zooArrayList = fillZoos();
    }

    @RequestMapping("/0_exam")
    public String exam(HttpServletRequest request, Model model){
        Visitor visitor = visitorArrayList.get(Integer.parseInt(request.getParameter("visitorIndex")));
        model.addAttribute("visitor",visitor);

        return "0_exam";
    }

    @RequestMapping("/1_newvisitor")
    public String newvisitor(Model model) {
        model.addAttribute("zooList",zooArrayList);
        model.addAttribute("staffList",staffArrayList);

        return "1_newvisitor";
    }
    @RequestMapping("/2_visitordetails")
    public String visitordetails(HttpServletRequest request, Model model) {

        String firstName = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        int yearOfBirth = Integer.parseInt(request.getParameter("birthyear"));
        int zooIndex = Integer.parseInt(request.getParameter("zooIndex"));
        int guideIndex = -1;
        String guideIndexString = request.getParameter("guideIndex");

        if (guideIndexString != null) {
            guideIndex = Integer.parseInt(guideIndexString);
            if (guideIndex < 0 ) {
                model.addAttribute("errormessage", "You didn't choose a guide!");
                return "error";
            }
        }

        Visitor visitor = new Visitor(firstName, surname);
        visitor.setYearOfBirth(yearOfBirth);
        zooArrayList.get(zooIndex).registerVisitor(visitor);
        visitor.setGuide(staffArrayList.get(guideIndex));
        visitorArrayList.add(visitor);
        model.addAttribute("visitor", visitor);

        return "0_exam";
    }
    @RequestMapping("/3_newstaff")
    public String newstaff() {
        return "3_newstaff";
    }
    @RequestMapping("/4_staffdetails")
    public String staffdetails(HttpServletRequest request, Model model) {

        String firstName = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        LocalDate employedSince = LocalDate.parse(request.getParameter("employedsince"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        boolean isStudent = (request.getParameter("workingstudent") != null);

        Staff staff = new Staff(firstName, surname);
        staff.setStartDate(employedSince);
        staff.setStudent(isStudent);
        staffArrayList.add(staff);

        model.addAttribute("staff", staff);
        return "4_staffdetails";
    }

    @RequestMapping("5_stafflist")
    public String stafflist(Model model) {

        model.addAttribute("staffList", staffArrayList);

        return "5_stafflist";
    }

    @RequestMapping("6_visitorlist")
    public String visitorlist(Model model) {

        model.addAttribute("visitorList", visitorArrayList);

        return "6_visitorlist";
    }
    @RequestMapping("7_newzoo")
    public String newzoo() {
        return "7_newzoo";
    }

    @RequestMapping("8_zoolist")
    public String zoolist(HttpServletRequest request,Model model) {
        String zooName = request.getParameter("zooname");
        if (zooName != null) {
            zooArrayList.add(new Zoo(zooName));
        }

        model.addAttribute("zooList",zooArrayList);
        return "8_zoolist";
    }

    @RequestMapping("9_newanimalworld")
    public String newanimalworld(Model model) {
        model.addAttribute("zooList",zooArrayList);
        model.addAttribute("staffList",staffArrayList);

        return "9_newanimalworld";
    }

    @RequestMapping("10_zoodetails")
    public String zoodetails(HttpServletRequest request,Model model) {
        String staffIndexS = request.getParameter("staffindex");
        int staffIndex = -1;
        if (staffIndexS != null) {
            staffIndex = Integer.parseInt(staffIndexS);
            if (staffIndex < 0 ) {
                model.addAttribute("errormessage", "You didn't choose a staff member!");
                return "error";
            }
        }
        String zooIndexS = request.getParameter("zooindex");
        int zooIndex = -1;
        if (zooIndexS != null) {
            zooIndex = Integer.parseInt(zooIndexS);
            if (zooIndex < 0) {
                model.addAttribute("errormessage", "You didn't choose a zoo!");
                return "error";
            }
        }

        String awName = request.getParameter("awname");
        if (awName != null) {
            int awAmount = Integer.parseInt(request.getParameter("awamount"));
            String awPhoto = request.getParameter("awphoto");
            Staff awStaff = staffArrayList.get(staffIndex);
            AnimalWorld animalWorld = new AnimalWorld(awName,awAmount);
            animalWorld.setResponsible(awStaff);
            animalWorld.setPhoto(awPhoto);
            zooArrayList.get(zooIndex).addAnimalWorld(animalWorld);
        }

        model.addAttribute("zoo",zooArrayList.get(Integer.parseInt(request.getParameter("zooindex"))));
        return "10_zoodetails";
    }
    @RequestMapping("11_animalworlddetails")
    public String animalworlddetails(HttpServletRequest request,Model model) {
        String searchTerm = request.getParameter("aw");
        if (searchTerm.equals("")) {
            model.addAttribute("errormessage","You didn't enter a search term!");
            return "error";
        }
        AnimalWorld animalWorld;
        for (Zoo zoo: zooArrayList) {
            animalWorld = zoo.searchAnimalWorldByName(searchTerm);
            if (animalWorld != null) {
                model.addAttribute("aw",animalWorld);
                return "11_animalworlddetails";
            }
        }
        model.addAttribute("errormessage","There is no animal world with name '"+searchTerm+"'");
        return "error";
    }
   private ArrayList<Staff> fillStaffMembers() {
        ArrayList<Staff> staffMembers = new ArrayList<>();

        Staff staff1 = new Staff("Johan", "Bertels");
        staff1.setStartDate(LocalDate.of(2002, 5, 1));
        Staff staff2 = new Staff("An", "Van Herck");
        staff2.setStartDate(LocalDate.of(2019, 3, 15));
        staff2.setStudent(true);
        Staff staff3 = new Staff("Bruno", "Coenen");
        staff3.setStartDate(LocalDate.of(1995,1,1));
        Staff staff4 = new Staff("Wout", "Dayaert");
        staff4.setStartDate(LocalDate.of(2002, 12, 15));
        Staff staff5 = new Staff("Louis", "Petit");
        staff5.setStartDate(LocalDate.of(2020, 8, 1));
        staff5.setStudent(true);
        Staff staff6 = new Staff("Jean", "Pinot");
        staff6.setStartDate(LocalDate.of(1999,4,1));
        Staff staff7 = new Staff("Ahmad", "Bezeri");
        staff7.setStartDate(LocalDate.of(2009, 5, 1));
        Staff staff8 = new Staff("Hans", "Volzky");
        staff8.setStartDate(LocalDate.of(2015, 6, 10));
        staff8.setStudent(true);
        Staff staff9 = new Staff("Joachim", "Henau");
        staff9.setStartDate(LocalDate.of(2007,9,18));
        staffMembers.add(staff1);
        staffMembers.add(staff2);
        staffMembers.add(staff3);
        staffMembers.add(staff4);
        staffMembers.add(staff5);
        staffMembers.add(staff6);
        staffMembers.add(staff7);
        staffMembers.add(staff8);
        staffMembers.add(staff9);
        return staffMembers;
    }

    private ArrayList<Visitor> fillVisitors() {
        ArrayList<Visitor> visitors = new ArrayList<>();
        Visitor visitor1 = new Visitor("Dominik", "Mioens");
        visitor1.setYearOfBirth(2001);
        visitor1.setGuide(staffArrayList.get(0));
        Visitor visitor2 = new Visitor("Zion", "Noops");
        visitor2.setYearOfBirth(1996);
        visitor2.setGuide(staffArrayList.get(1));
        Visitor visitor3 = new Visitor("Maria", "Bonetta");
        visitor3.setYearOfBirth(1998);
        visitor3.setGuide(staffArrayList.get(2));
        Visitor visitor4 = new Visitor("Jeff", "Verheyen");
        visitor4.setYearOfBirth(1997);
        visitor4.setGuide(staffArrayList.get(3));
        visitors.add(visitor1);
        visitors.add(visitor2);
        visitors.add(visitor3);
        visitors.add(visitor4);
        visitors.get(0).addToWishList("Dolphin");
        visitors.get(0).addToWishList("Snake");
        visitors.get(1).addToWishList("Lion");
        visitors.get(1).addToWishList("Eagle");
        visitors.get(1).addToWishList("Monkey");
        visitors.get(1).addToWishList("Elephant");
        visitors.get(2).addToWishList("Turtle");
        visitors.get(3).addToWishList("Frog");
        visitors.get(3).addToWishList("Parrot");
        visitors.get(3).addToWishList("Panther");
        return visitors;
    }

    private ArrayList<Zoo> fillZoos() {
        ArrayList<Zoo> zoos = new ArrayList<>();
        Zoo zoo1 = new Zoo("Antwerp Zoo");
        Zoo zoo2 = new Zoo("Bellewaerde");
        Zoo zoo3 = new Zoo("Plankendael Zoo");
        AnimalWorld animalWorld1 = new AnimalWorld("Aquarium");
        AnimalWorld animalWorld2 = new AnimalWorld("Reptiles");
        AnimalWorld animalWorld3 = new AnimalWorld("Monkeys");
        animalWorld1.setNumberOfAnimals(1250);
        animalWorld2.setNumberOfAnimals(500);
        animalWorld3.setNumberOfAnimals(785);
        animalWorld1.setPhoto("/img/aquarium.jpg");
        animalWorld2.setPhoto("/img/reptiles.jpg");
        animalWorld3.setPhoto("/img/monkeys.jpg");
        animalWorld1.setResponsible(staffArrayList.get(0));
        animalWorld2.setResponsible(staffArrayList.get(1));
        animalWorld3.setResponsible(staffArrayList.get(2));
        zoo1.addAnimalWorld(animalWorld1);
        zoo1.addAnimalWorld(animalWorld2);
        zoo1.addAnimalWorld(animalWorld3);
        zoo2.addAnimalWorld(animalWorld1);
        zoo2.addAnimalWorld(animalWorld2);
        zoo3.addAnimalWorld(animalWorld1);
        zoo3.addAnimalWorld(animalWorld3);
        zoos.add(zoo1);
        zoos.add(zoo2);
        zoos.add(zoo3);
        return zoos;
    }
}
