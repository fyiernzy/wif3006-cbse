package assignment.wif3006cbse.features.sample.mapper;

import assignment.wif3006cbse.features.sample.domain.Sample;
import assignment.wif3006cbse.features.sample.domain.SampleStatusEnum;
import assignment.wif3006cbse.features.sample.dto.CreateSampleModel;
import assignment.wif3006cbse.features.sample.dto.SampleModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T17:31:40+0800",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class SampleMapperImpl implements SampleMapper {

    @Override
    public SampleModel toModel(Sample sample) {
        if ( sample == null ) {
            return null;
        }

        Long id = null;
        String sampleValue = null;
        SampleStatusEnum sampleStatusEnum = null;

        if ( sample.getId() != null ) {
            id = sample.getId();
        }
        if ( sample.getSampleValue() != null ) {
            sampleValue = sample.getSampleValue();
        }
        if ( sample.getSampleStatusEnum() != null ) {
            sampleStatusEnum = sample.getSampleStatusEnum();
        }

        SampleModel sampleModel = new SampleModel( id, sampleValue, sampleStatusEnum );

        return sampleModel;
    }

    @Override
    public Sample toEntity(CreateSampleModel createSampleModel) {
        if ( createSampleModel == null ) {
            return null;
        }

        Sample sample = new Sample();

        if ( createSampleModel.sampleStatusEnum() != null ) {
            sample.setSampleStatusEnum( createSampleModel.sampleStatusEnum() );
        }
        if ( createSampleModel.sampleValue() != null ) {
            sample.setSampleValue( createSampleModel.sampleValue() );
        }

        return sample;
    }
}
