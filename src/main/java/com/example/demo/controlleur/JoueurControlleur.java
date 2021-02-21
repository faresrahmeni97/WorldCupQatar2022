package com.example.demo.controlleur;
import com.example.demo.entities.Joueur;
import com.example.demo.repository.JoueurRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.*;
import java.nio.file.*;
import javax.validation.Valid;

import java.io.IOException;
import java.util.List;
@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping("/api")

public class JoueurControlleur {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    JoueurRepository joueurR;
    private JoueurControlleur FileUploadUtil;

    @PostMapping("/joueurs/save")
    public RedirectView saveUser(Joueur joueur,
                                 @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        joueur.setPhotos(fileName);

        Joueur savedUser = joueurR.save(joueur);

        String uploadDir = "joueur-photos/" + savedUser.getId();
        
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
      // FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/joueurs", true);
    }
    @GetMapping("/joueurs")

    public List<Joueur> getAllUsers() {
        List <Joueur> pro = joueurR.findAll();

        for (Joueur joueur : pro) {
            logger.debug("log:     " + joueur);
            System.out.println("sysout:   " + joueur);

        }
        return pro;

    }
    @PostMapping("/addjoueur")

    public Joueur createJoueur(@Valid @RequestBody Joueur joueur) {
        return joueurR.save(joueur);
    }


    @GetMapping("/joueur/{id}")
    public Joueur getUserById(@PathVariable(value = "id") Long Id) {
        return joueurR.findById(Id).orElseThrow(null);
        // .orElseThrow(() -> new ResourceNotFoundException("Joueur", "id", Id));
    }

    @DeleteMapping("/joueurdelete/{id}")
    public ResponseEntity<?> deleteJoueur(@PathVariable(value = "id") Long joueurId) {
        Joueur joueur = joueurR.findById(joueurId).orElseThrow(null);
        //.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        // userRepository.deleteById(userId);
        joueurR.delete(joueur);

        return ResponseEntity.ok().build();
    }
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    @PutMapping("/joueur/{id}")
    public Joueur updateJoueur(@PathVariable(value = "id") Long Id,
                           @Valid @RequestBody Joueur userDetails) {

        Joueur joueur = joueurR.findById(Id).orElseThrow(null);


        joueur.setClubjoueur(userDetails.getclubjoueur());
        joueur.setNumposte(userDetails.getnumposte());
        joueur.setPoste(userDetails.getposte());
        joueur.setPhotos(userDetails.getPhotos());

        Joueur updatedJoueur = joueurR.save(joueur);
        return updatedJoueur;
    }

}
