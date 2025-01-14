package com.atlantis.service.Entry;

import com.atlantis.model.Entry.Entry;
import com.atlantis.model.Entry.EntryDetails;
import com.atlantis.model.Tag.Tag;
import com.atlantis.repository.Entry.EntryDetailsRepository;
import com.atlantis.repository.Entry.EntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class EntryService {
    private final EntryRepository entryRepository;
    private final EntryDetailsRepository entryDetailsRepository;

    public EntryService(EntryRepository entryRepository,
                        EntryDetailsRepository entryDetailsRepository){
        this.entryRepository = entryRepository;
        this.entryDetailsRepository = entryDetailsRepository;
    }
    public List<Entry> getAllEntries(){
        return entryRepository.findAll();
    }

    public Entry getEntry(String entryId){
        return entryRepository.findEntryByEntryId(entryId).orElseThrow(()->
                new IllegalStateException("Entry does not exist"));
    }

    public void addNewEntry(Entry entry){
        Optional<Entry> entryExist = entryRepository.findEntryByEntryId(entry.getEntryId());
        if(entryExist.isPresent()){
            throw new IllegalStateException(
                    "There is already a Entry with the given id!");
        }
        entryRepository.save(entry);
        System.out.println(entry);
    }

    @Transactional
    public void deleteEntry(String entryId){
        boolean exist = entryRepository.existsEntryByEntryId(entryId);
        if(!exist){
            throw new IllegalStateException("Entry with entryId="+ entryId+" does not exist");
        }
        entryRepository.deleteEntryByEntryId(entryId);
    }

    @Transactional
    public void updateEntry(String entryId, Optional<String> entryTitle,
                            Optional<String> entryParent,
                            Optional<String> entryType,
                            Optional<Set<Tag>> entryTags,
                            Optional<String> entryCategory,
                            Optional<String> entryContent) {

        Entry entry = entryRepository.findEntryByEntryId(entryId).orElseThrow(()->
                new IllegalStateException("Entry with entryId="+ entryId+" does not exist"));
        String entry_det_id = entry.getEntryDetails().getEntryDetailId();
        EntryDetails entryDetails = entryDetailsRepository.findEntryDetailsByEntryDetailId(entry_det_id).orElseThrow(()->
                new IllegalStateException("EntryDetail with entryDetailId="+ entry_det_id+" does not exist"));

        /*Entry table update*/
        entryTitle.ifPresent(entry::setEntryTitle);
        entryParent.ifPresent(entry::setEntryParent);
        entryType.ifPresent(entry::setEntryType);

        /*EntryDetails table update*/
        entryTags.ifPresent(entryDetails::setEntryTags);
        entryCategory.ifPresent(entryDetails::setEntryCategory);
        entryContent.ifPresent(entryDetails::setEntryContent);

    }

    public Map getJoinedDetails(String entryId){
        return entryDetailsRepository.getJoinedDetailsByEntryId(entryId).orElseThrow(()->
                new IllegalStateException("Entry does not exist"));
    }

}
