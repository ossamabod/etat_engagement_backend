package ma.fst.etatdengagement.Services.Impl;

import ma.fst.etatdengagement.Adapter.GradeAdapter;
import ma.fst.etatdengagement.DTO.Grade.GradeDto;
import ma.fst.etatdengagement.DTO.Grade.SearchGradeDto;
import ma.fst.etatdengagement.Models.Grade;
import ma.fst.etatdengagement.Repository.GradeRepository;
import ma.fst.etatdengagement.Repository.IndemnitesRepository;
import ma.fst.etatdengagement.Repository.RetenueRepository;
import ma.fst.etatdengagement.Services.GradeService;
import ma.fst.etatdengagement.Specification.GradeSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final IndemnitesRepository indemnitesRepository;
    private final RetenueRepository retenueRepository;
    private final GradeSpecification gradeSpecification;
    private final GradeAdapter gradeAdapter;

    public GradeServiceImpl(GradeRepository gradeRepository, IndemnitesRepository indemnitesRepository, RetenueRepository retenueRepository, GradeSpecification gradeSpecification, GradeAdapter gradeAdapter) {
        this.gradeRepository = gradeRepository;
        this.indemnitesRepository = indemnitesRepository;
        this.retenueRepository = retenueRepository;
        this.gradeSpecification = gradeSpecification;
        this.gradeAdapter = gradeAdapter;
    }


    @Override
    public List<GradeDto> searchByCriteria(SearchGradeDto searchGradeDto){


        List<Grade> gradeList = gradeRepository.findAll(
                gradeSpecification.searchByCriteria(searchGradeDto)
        );

        return gradeList.stream().map(gradeAdapter::fromGradeToGradeDto).collect(Collectors.toList());
    };


    @Override
    public Grade createGradeWithDetails(Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Grade cannot be null.");
        }

        if (grade.getIndemnites() != null) {
            grade.getIndemnites().setGrade(grade);
        }

        if (grade.getRetenue() != null) {
            grade.getRetenue().setGrade(grade);
        }

        return gradeRepository.save(grade);
    }
    @Override
    public Optional<Grade> updateGradeWithDetails(long id, Grade grade) {
        return gradeRepository.findById(id).map(existingGrade -> {
            existingGrade.setCode(grade.getCode());
            existingGrade.setLibelle(grade.getLibelle());
            existingGrade.setNiveau(grade.getNiveau());
            existingGrade.setTraitementBase(grade.getTraitementBase());

            // Update indemnities and retenues
            if (grade.getIndemnites() != null) {
                grade.getIndemnites().setGrade(existingGrade);
                existingGrade.setIndemnites(grade.getIndemnites());
            }

            if (grade.getRetenue() != null) {
                grade.getRetenue().setGrade(existingGrade);
                existingGrade.setRetenue(grade.getRetenue());
            }

            return gradeRepository.save(existingGrade);
        });
    }

    @Override
    public boolean deleteGrade(Long id) {
        return gradeRepository.findById(id).map(grade -> {
            gradeRepository.delete(grade);
            return true;
        }).orElse(false);
    }
}
